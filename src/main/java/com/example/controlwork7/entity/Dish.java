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
}
