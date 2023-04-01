package com.example.controlwork7.service;

import com.example.controlwork7.dao.RestaurantDao;
import com.example.controlwork7.dto.RestaurantDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantDao restaurantDao;

    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }
    public List<RestaurantDto> getAllRestaurants() {
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        for (int i = 0; i < restaurantDao.getAll().size(); i++) {
            restaurantDtos.add(RestaurantDto.from(restaurantDao.getAll().get(i)));
        }
        return restaurantDtos;
    }
}
