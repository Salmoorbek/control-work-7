package com.example.controlwork7.controller;

import com.example.controlwork7.dto.OrderDto;
import com.example.controlwork7.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public OrderDto createOrder(@PathVariable int dishId, int restaurantId, Authentication authentication) {
        return orderService.createOrder(dishId, restaurantId, authentication);
    }
    @GetMapping()
    public List<OrderDto> getOrders(Authentication authentication) {
        return orderService.getClientOrders(authentication);
    }
}
