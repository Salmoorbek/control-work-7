package com.example.controlwork7.controller;

import com.example.controlwork7.dto.DishDto;
import com.example.controlwork7.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<DishDto>> getRestaurant(@PathVariable int restaurantId) {
        if(dishService.checkRestaurant(restaurantId))
            return new ResponseEntity<>(dishService.getDishesByRestaurantId(restaurantId), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
