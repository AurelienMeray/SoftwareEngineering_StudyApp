package com.example.demo.sbdata;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class DataController {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public <T> T getFirstResult(String sql, RowMapper rowMapper) {
        List<T> resultSet = jdbcTemplate.query(sql,
                rowMapper);

        if (resultSet.isEmpty()) {
            return null;
        }

        return resultSet.get(0);
    }

    public <T> T getFirstResult(String sql, Object[] params, RowMapper rowMapper) {
        List<T> resultSet = jdbcTemplate.query(sql,
                params,
                new UserRowMapper());

        if (resultSet.isEmpty()) {
            return null;
        }

        return resultSet.get(0);
    }

    public <T> List<T> getResultSet(String sql, RowMapper rowMapper) {
        List<T> resultSet = jdbcTemplate.query(sql,
                new UserRowMapper());
        return resultSet;
    }

    public <T> List<T> getResultSet (String sql, Object[] params, RowMapper rowMapper) {
        List<T> resultSet = jdbcTemplate.query(sql,
                params,
                new UserRowMapper());
        return resultSet;
    }

    public void update(String sql, Object[] params) {
        jdbcTemplate.update(sql, params);
    }

    public void update(String sql) {
        jdbcTemplate.update(sql);
    }

    /*
    public int checkForUser(User user) {
        int result = 0;
        String sql = "SELECT * FROM \"sbdatabase\".\"USER\" u WHERE u.username = ? AND u.password = ?";
        if (getFirstResult(sql,
                new Object[]{user.getUserName(), user.getPassword()}, new UserRowMapper()) != null) {
            result = 1;
        }

        return result;
    }
    */

    /**
     * Adds a user to the user table.
     *
     * @return 0 if failed, 1 if succeeded
     */
    public int saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        String sql = "INSERT INTO \"sbdatabase\".\"ROOM\"\n" +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        try {
            update(sql, new Object[]{user.getUserName(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword()
            });
            result = 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     *  Adds a room to this user's room list.
     *  @param user the user that will be part of the room
     *  @param room the room to add
     */

    public int updateUserRooms(User user, Room room) {
        if (user == null || room == null) throw new IllegalArgumentException("User and room cannot be null");
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Z"));
        String sql = "INSERT INTO \"sbdatabase\".\"USERROOM\"\n" +
                "VALUES (?, ?, ?)";
        int result = 0;
        try {
            update(sql, new Object[]{user.getUserName(),
                    room.getRoomId(),
                    currentTime,
            });
            result = 1;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }


    public User getUserInfo(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username cannot be null");
        }
        User loginInfo;
        String sql = "SELECT * FROM \"sbdatabase\".\"USER\" u WHERE u.username = ?";
        loginInfo = getFirstResult(sql, new Object[]{username}, new UserRowMapper());

        if (loginInfo == null) {
            loginInfo = new User();
        }
        return loginInfo;
    }
}
