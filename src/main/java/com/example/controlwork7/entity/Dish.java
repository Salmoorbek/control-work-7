package com.example.controlwork7.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Dish {
    private int id;
    private String name;
    private String type;
    private double price;
    private int restaurantId;

    public Dish(int id, String name, String type, double price, int restaurantId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.restaurantId = restaurantId;
    }
}
