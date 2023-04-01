package com.example.controlwork7.controller;

import com.example.controlwork7.dto.DishDto;
import com.example.controlwork7.dto.OrderDto;
import com.example.controlwork7.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/restaurantDishes")
    public List<DishDto> getRestaurant(@RequestParam int restaurantId) {
        return dishService.getDishesByRestaurantId(restaurantId);
    }
}
