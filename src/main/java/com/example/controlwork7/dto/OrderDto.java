package com.example.controlwork7.dto;

import com.example.controlwork7.entity.Order;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class OrderDto {
    private int clientId;
    private int dishId;
    private int restaurantId;
    private LocalDate orderDate;
    public static OrderDto from(Order order) {
        return builder()
                .clientId(order.getClientId())
                .restaurantId(order.getRestaurantId())
                .dishId(order.getDishId())
                .orderDate(order.getDate())
                .build();
    }
}
