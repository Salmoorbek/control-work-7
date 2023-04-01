package com.example.controlwork7.service;

import com.example.controlwork7.dao.DishDao;
import com.example.controlwork7.dto.DishDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {
    private final DishDao dishDao;

    public DishService(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public List<DishDto> getDishesByRestaurantId(int restaurantId) {
        List<DishDto> dishDtoList = new ArrayList<>();
        for (int i = 0; i < dishDao.getByRestaurantId(restaurantId).size(); i++) {
            dishDtoList.add(DishDto.from(dishDao.getByRestaurantId(restaurantId).get(i)));
        }
        return dishDtoList;
    }
}
