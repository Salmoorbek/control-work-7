package com.example.controlwork7.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@RequiredArgsConstructor
public class Order {
    private int id;
    private int clientId;
    private int restaurantId;
    private int dishId;
    private LocalDate orderDate;

    public Order(int id, int clientId, int restaurantId, int dishId, LocalDate orderDate) {
        this.id = id;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.dishId = dishId;
        this.orderDate = orderDate;
    }
}
