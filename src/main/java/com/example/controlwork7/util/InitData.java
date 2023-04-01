package com.example.controlwork7.util;

import com.example.controlwork7.dao.ClientDao;
import com.example.controlwork7.dao.DishDao;
import com.example.controlwork7.dao.OrderDao;
import com.example.controlwork7.dao.RestaurantDao;
import com.example.controlwork7.entity.Client;
import com.example.controlwork7.entity.Dish;
import com.example.controlwork7.entity.Order;
import com.example.controlwork7.entity.Restaurant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class InitData {
    @Bean
    CommandLineRunner init(ClientDao clientDao, DishDao dishDao, OrderDao orderDao, RestaurantDao restaurantDao) {
        return (args) -> {
            clientDao.createTable();
            restaurantDao.createTable();
            dishDao.createTable();
            orderDao.createTable();

            orderDao.deleteAll();
            dishDao.deleteAll();
            clientDao.deleteAll();
            restaurantDao.deleteAll();

            clientDao.alertSequenceUser();
            restaurantDao.alertSequenceRestaurant();
            dishDao.alertSequenceDish();
            orderDao.alertSequenceOrder();

            clientDao.save(creatUsers());
            restaurantDao.saveRestaurants(creatRestaurant());
            dishDao.saveDish(creatDish());
            orderDao.saveOrder(creatOrder());

        };
    }
    public List<Client> creatUsers(){
        List<Client> users = new ArrayList<>();
        users.add(new Client(1,"salmor","a@a.a","123",true,"USER"));
        users.add(new Client(2,"ss","s@s.s","123",true,"USER"));
        users.add(new Client(3,"aaa","d@d.d","123",true,"USER"));
        return users;
    }
    public List<Dish> creatDish(){
        List<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish(1,"плов","обед", 100, 1));
        dishes.add(new Dish(2,"логман","ужин", 120, 1));
        dishes.add(new Dish(3,"суп","завтрак", 50, 2));
        return dishes;
    }
    public List<Order> creatOrder(){
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1,1,1,1, LocalDate.of(2023,12,21)));
        orders.add(new Order(2,1,2,1, LocalDate.of(2022,12,21)));
        orders.add(new Order(3,2,3,2, LocalDate.of(2022,12,21)));
        return orders;
    }
    public List<Restaurant> creatRestaurant(){
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1,"Нават","укйуцкуцйккйцукцйкцукц"));
        restaurants.add(new Restaurant(2,"Пишпек","ммфвумкфукмуфкмфкм"));
        restaurants.add(new Restaurant(3,"Шашлычная №1","мукмуфмумуфму"));
        return restaurants;
    }
}
