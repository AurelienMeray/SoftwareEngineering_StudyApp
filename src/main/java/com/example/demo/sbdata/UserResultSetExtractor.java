package com.example.demo.sbdata;

import com.example.demo.model.User;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetExtractor implements ResultSetExtractor {

    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserName(rs.getString(1));
        user.setFirstName(rs.getString(2));
        user.setLastName(rs.getString(3));
        user.setEmail(rs.getString(4));
        user.setPassword(rs.getString(5));
        return user;
    }
}