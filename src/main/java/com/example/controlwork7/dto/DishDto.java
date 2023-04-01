package com.example.controlwork7.dto;

import com.example.controlwork7.entity.Dish;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class DishDto {
    private String name;
    private String type;
    private double price;
    private int restaurantId;
    public static DishDto from(Dish dish){
        return builder()
                .name(dish.getName())
                .type(dish.getType())
                .price(dish.getPrice())
                .restaurantId(dish.getRestaurantId())
                .build();
    }
}
