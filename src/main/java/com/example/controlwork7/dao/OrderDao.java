package com.example.controlwork7.dao;

import com.example.controlwork7.entity.Dish;
import com.example.controlwork7.entity.Order;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrderDao extends BaseDao{
    public OrderDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.update("CREATE TABLE if not exists orders ( " +
                "  id SERIAL PRIMARY KEY , " +
                "  client_id INT NOT NULL, " +
                "  dish_id INT NOT NULL, " +
                "  restaurant_id INT NOT NULL, " +
                "  order_date DATE NOT NULL, " +
                "  FOREIGN KEY (client_id) REFERENCES users(id)," +
                "  FOREIGN KEY (restaurant_id) REFERENCES restaurant(id), " +
                "  FOREIGN KEY (dish_id) REFERENCES dish(id) " +
                ");");
    }
    public void saveOrder(List<Order> order) {
        String sql = "INSERT INTO orders (client_id, restaurant_id , dish_id, order_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, order.get(i).getClientId());
                ps.setInt(2, order.get(i).getRestaurantId());
                ps.setInt(3, order.get(i).getDishId());
                ps.setDate(4, Date.valueOf(order.get(i).getDate()));
            }

            @Override
            public int getBatchSize() {
                return order.size();
            }
        });
    }
    public void deleteAll() {
        String sql = "delete from orders";
        jdbcTemplate.update(sql);
    }
    public void alertSequenceOrder() {
        String sql = "alter sequence orders_id_seq restart with 1 ";
        jdbcTemplate.update(sql);
    }
    public void createNewOrder(Order order) {
        String sql = "INSERT INTO orders (client_id, restaurant_id, dish_id, order_date) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getRestaurantId());
            ps.setInt(3, order.getDishId());
            ps.setDate(4, Date.valueOf(order.getDate()));
            return  ps;
        });
    }
//    public List<Dish> checkDishInRestaurant(int restaurantId) {
//        String sql = "SELECT * FROM  orders " +
//                " WHERE restaurant_id = ? ";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dish.class), restaurantId);
//    }
    public List<Dish> checkOrderByDishIdAndRestaurantId(int dishId, int restaurantId) {
        String sql = "SELECT * FROM dish WHERE id = ? and restaurant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dish.class), dishId, restaurantId);
    }
    public int getUserIdByUserName(String email){
        String sql = "SELECT id FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, email);
    }
    public List<Order> getClientOrders(String email){
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Order.class), email);
    }
}
