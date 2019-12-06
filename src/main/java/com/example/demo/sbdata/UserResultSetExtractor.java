package com.example.demo.sbdata;

import com.example.demo.model.User;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserName(rs.getString("username"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}