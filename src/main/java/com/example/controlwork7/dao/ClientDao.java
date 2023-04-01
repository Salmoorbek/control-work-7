package com.example.controlwork7.dao;

import com.example.controlwork7.entity.Client;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class ClientDao extends BaseDao{
    private final PasswordEncoder encoder;
    public ClientDao(JdbcTemplate jdbcTemplate, PasswordEncoder encoder) {
        super(jdbcTemplate);
        this.encoder = encoder;
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE if not exists users( " +
                "  id SERIAL PRIMARY KEY NOT NULL, " +
                "  email VARCHAR(255) NOT NULL, " +
                "  name VARCHAR(255) NOT NULL, " +
                "  enabled boolean NOT NULL, " +
                "  password VARCHAR(255) NOT NULL, " +
                "  role text" +
                ");");
    }
    public void save(List<Client> users) {
        String sql = "INSERT INTO users (email, name, enabled, password, role) " +
                "VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, users.get(i).getEmail());
                ps.setString(2, users.get(i).getName());
                ps.setBoolean(3, Boolean.TRUE);
                ps.setString(4, encoder.encode(users.get(i).getPassword()));
                ps.setString(5, "USER");
            }
            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
    }
    public void createNewUser(Client user) {
        String sql = "INSERT INTO users (email, name, enabled, password, role) " +
                "VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setBoolean(3, Boolean.TRUE);
            ps.setString(4, this.encoder.encode(user.getPassword()));
            ps.setString(5, "USER");
            return ps;
        });
    }
    public void deleteAll() {
        String sql = "delete from users";
        jdbcTemplate.update(sql);
    }
    public void alertSequenceUser() {
        String sql = "alter sequence users_id_seq restart with 1 ";
        jdbcTemplate.update(sql);
    }
    public Client getUsersByEmail(String email) {
        String sql = "SELECT * FROM users where email = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Client.class), email);
    }
}
