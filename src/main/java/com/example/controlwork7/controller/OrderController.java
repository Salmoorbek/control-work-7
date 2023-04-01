package com.example.controlwork7.controller;

import com.example.controlwork7.dto.OrderDto;
import com.example.controlwork7.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestParam int dishId,
                                               @RequestParam int restaurantId,
                                               Authentication authentication) {
        if (orderService.createOrder(dishId, restaurantId, authentication) != null)
            return new ResponseEntity<>(orderService.createOrder(dishId, restaurantId, authentication), HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @GetMapping
    public List<OrderDto> getOrders(Authentication authentication) {
        return orderService.getClientOrders(authentication);
    }
}
