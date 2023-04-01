package com.example.controlwork7.dto;

import com.example.controlwork7.entity.Restaurant;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE,force = true)
public class RestaurantDto {
    private int id;
    private String name;
    private String description;
    public static RestaurantDto from(Restaurant restaurant) {
        return builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .build();
    }
}
