package com.example.controlwork7.service;

import com.example.controlwork7.dao.OrderDao;
import com.example.controlwork7.dto.OrderDto;
import com.example.controlwork7.entity.Order;
import com.example.controlwork7.exception.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderDto createOrder(int dishId, int restaurantId, Authentication authentication) throws ResourceNotFoundException {
        if (orderDao.checkOrderByDishIdAndRestaurantId(dishId, restaurantId).isEmpty()) {
            throw new ResourceNotFoundException("Блюдо: " + dishId + " не найдено");
        }
        User user = (User) authentication.getPrincipal();
        int userId = orderDao.getUserIdByUserName(user.getUsername());
        var order = Order.builder()
                        .clientId(userId)
                        .dishId(dishId)
                        .restaurantId(restaurantId)
                        .orderDate(LocalDate.now())
                        .build();
        orderDao.createNewOrder(order);
        return OrderDto.from(order);
    }

    public List<OrderDto> getClientOrders(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (int i = 0; i < orderDao.getClientOrders(user.getUsername()).size(); i++) {
            orderDtos.add(OrderDto.from(orderDao.getClientOrders(user.getUsername()).get(i)));
        }
        return orderDtos;
    }
}
