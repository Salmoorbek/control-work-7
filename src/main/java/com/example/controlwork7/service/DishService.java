package com.example.controlwork7.service;

import com.example.controlwork7.dao.DishDao;
import com.example.controlwork7.dto.DishDto;
import com.example.controlwork7.exception.ResourceNotFoundException;
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
//        for (int i = 0; i < dishDao.getAllDishes().size(); i++) {
//            if(dishDao.getDishesByRestaurantId(restaurantId).get(i) == dishDao.getAllDishes().get(i)){
//                throw new ResourceNotFoundException("Такого ресторана нет ");
//            }
//        }
        if(dishDao.getDishesByRestaurantId(restaurantId).isEmpty()){
            throw new ResourceNotFoundException("Такого ресторана нет ");
        }
        List<DishDto> dishDtoList = new ArrayList<>();
        for (int i = 0; i < dishDao.getDishesByRestaurantId(restaurantId).size(); i++) {
            dishDtoList.add(DishDto.from(dishDao.getDishesByRestaurantId(restaurantId).get(i)));
        }
        return dishDtoList;
    }
}
