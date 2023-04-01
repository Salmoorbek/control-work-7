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
    private int dishId;
    private LocalDate date;
}
