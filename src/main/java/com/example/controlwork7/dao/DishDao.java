package com.example.controlwork7.dao;

import com.example.controlwork7.entity.Dish;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class DishDao extends BaseDao{
    public DishDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
    @Override
    public void createTable() {
        jdbcTemplate.update("CREATE TABLE if not exists dish ( " +
                "    id SERIAL NOT NULL PRIMARY KEY, " +
                "    name VARCHAR(255) NOT NULL, " +
                "    type VARCHAR(255) NOT NULL, " +
                "    price FLOAT NOT NULL, " +
                "    restaurant_id INT NOT NULL, " +
                "    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id) " +
                ");");
    }
    public void saveDish(List<Dish> dish) {
        String sql = "INSERT INTO dish (name, type, price, restaurant_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, dish.get(i).getName());
                ps.setString(2, dish.get(i).getType());
                ps.setDouble(3, dish.get(i).getPrice());
                ps.setLong(4, dish.get(i).getRestaurantId());
            }

            @Override
            public int getBatchSize() {
                return dish.size();
            }
        });
    }
    public void deleteAll() {
        String sql = "delete from dish";
        jdbcTemplate.update(sql);
    }
    public void alertSequenceDish() {
        String sql = "alter sequence dish_id_seq restart with 1 ";
        jdbcTemplate.update(sql);
    }
    public List<Dish> getByRestaurantId(int restaurantId) {
        String sql = "SELECT * FROM dish WHERE restaurant_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dish.class), restaurantId);
    }
}
