package com.example.controlwork7.dto;

import com.example.controlwork7.entity.Order;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class OrderDto {
    private int id;
    private int clientId;
    private int dishId;
    private LocalDate orderDate;
    public static OrderDto from(Order order) {
        return builder()
                .id(order.getId())
                .clientId(order.getClientId())
                .dishId(order.getDishId())
                .orderDate(order.getDate())
                .build();
    }
}
