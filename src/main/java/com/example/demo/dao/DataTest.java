package com.example.demo.dao;

import com.example.demo.model.User;
import com.example.demo.sbdata.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository("postgrestest")
public class DataTest implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    //@Autowired
    public DataTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> selectAllUsers() {
        final String sql = "SELECT * FROM \"sbdatabase\".\"USER\"";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }
}
