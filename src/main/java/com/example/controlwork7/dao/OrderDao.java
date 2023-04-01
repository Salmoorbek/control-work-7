package com.example.controlwork7.dao;

import com.example.controlwork7.entity.Order;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
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
                "  order_date DATE NOT NULL, " +
                "  FOREIGN KEY (client_id) REFERENCES users(id), " +
                "  FOREIGN KEY (dish_id) REFERENCES dish(id) " +
                ");");
    }
    public void saveOrder(List<Order> order) {
        String sql = "INSERT INTO orders (client_id, dish_id, order_date) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, order.get(i).getClientId());
                ps.setLong(2, order.get(i).getDishId());
                ps.setDate(3, Date.valueOf(order.get(i).getDate()));
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
}
