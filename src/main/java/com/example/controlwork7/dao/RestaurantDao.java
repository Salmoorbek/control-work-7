package com.example.controlwork7.dao;

import com.example.controlwork7.entity.Restaurant;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class RestaurantDao extends BaseDao{
    public RestaurantDao(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.update("CREATE TABLE if not exists restaurant ( " +
                "  id SERIAL PRIMARY KEY, " +
                "  name VARCHAR(255) NOT NULL, " +
                "  description VARCHAR(500) " +
                ");");
    }
    public void saveRestaurants(List<Restaurant> restaurant) {
        String sql = "INSERT INTO restaurant (id, name, description) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, restaurant.get(i).getId());
                ps.setString(2, restaurant.get(i).getName());
                ps.setString(3, restaurant.get(i).getDescription());
            }

            @Override
            public int getBatchSize() {
                return restaurant.size();
            }
        });
    }
    public void deleteAll() {
        String sql = "delete from restaurant";
        jdbcTemplate.update(sql);
    }
    public void alertSequenceRestaurant() {
        String sql = "alter sequence restaurant_id_seq restart with 1 ";
        jdbcTemplate.update(sql);
    }

    public List<Restaurant> getAll() {
        String sql = "SELECT * FROM restaurant";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Restaurant.class));
    }
    public Restaurant getById(int id) {
        String sql = "SELECT * FROM restaurant WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Restaurant.class), id);
    }
}
