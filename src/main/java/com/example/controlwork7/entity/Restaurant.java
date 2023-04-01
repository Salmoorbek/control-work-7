package com.example.controlwork7.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Restaurant {
    private int id;
    private String name;
    private String description;

    public Restaurant(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
