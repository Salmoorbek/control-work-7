package com.example.controlwork7.controller;

import com.example.controlwork7.dto.RestaurantDto;
import com.example.controlwork7.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantDto> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

}