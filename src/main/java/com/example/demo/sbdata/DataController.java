package com.example.demo.sbdata;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                rowMapper);

        if (resultSet.isEmpty()) {
            return null;
        }

        return resultSet.get(0);
    }

    public <T> List<T> getResultSet(String sql, RowMapper rowMapper) {
        List<T> resultSet = jdbcTemplate.query(sql,
                rowMapper);
        return resultSet;
    }

    public <T> List<T> getResultSet (String sql, Object[] params, RowMapper rowMapper) {
        List<T> resultSet = jdbcTemplate.query(sql,
                params,
                rowMapper);
        return resultSet;
    }

    public void update(String sql, Object[] params) {
        jdbcTemplate.update(sql, params);
    }

    public void update(String sql) {
        jdbcTemplate.update(sql);
    }

    public int checkForUser(User user) {
        int result = 0;
        String sql = "SELECT * FROM \"sbdatabase\".\"USER\" u WHERE u.username = ? AND u.password = ?";
        if (getFirstResult(sql,
                new Object[]{user.getUserName(), user.getPassword()}, new UserRowMapper()) != null) {
            result = 1;
        }

        return result;
    }

    /**
     * Adds a user to the user table.
     *
     * @return 0 if failed, 1 if succeeded
     */
    public int saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        String sql = "INSERT INTO \"sbdatabase\".\"USER\"\n" +
                     "VALUES (?, ?, ?, ?, ?)";
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
            Logger.getLogger(DataController.class.getName()).log(Level.INFO, null, e);
            //e.printStackTrace();
        }
        return result;
    }

    public int saveRoom(User user, Room room) {
        if (user == null || room == null) {
            throw new IllegalArgumentException("User and room cannot be null");
        }
        String sql = "INSERT INTO \"sbdatabase\".\"ROOM\"\n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        int result = 0;
        try {
            update(sql, new Object[]{room.getRoomId(),
                    user.getUserName(),
                    room.getRoomName(),
                    room.getSubject(),
                    room.getLocation(),
                    room.getDescription()
            });
            updateUserRooms(user, room);
            result = 1;
        } catch (DataAccessException e) {
            Logger.getLogger(DataController.class.getName()).log(Level.INFO, null, e);
            //e.printStackTrace();
        }
        return result;
    }

    /**
     *  Adds a room to this user's room list.
     *  @param user the user that will be part of the room
     *  @param room the room to add
     */

    public int updateUserRooms(User user, Room room) {
        if (user == null || room == null) {
            throw new IllegalArgumentException("User and room cannot be null");
        }
        ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("Z"));
        LocalDate currentDate = currentDateTime.toLocalDate();

        String sql = "INSERT INTO \"sbdatabase\".\"USERROOM\"\n" +
                "VALUES (?, ?, ?)";
        int result = 0;
        try {
            update(sql, new Object[]{user.getUserName(),
                    room.getRoomId(),
                    currentDate.toString(),
            });
            result = 1;
        } catch (DataAccessException e) {
            Logger.getLogger(DataController.class.getName()).log(Level.INFO, null, e);
            //e.printStackTrace();
        }
        return result;
    }


    /**
     * Returns all the rooms that the user has joined.
     * @param user topic of the room being searched for
     * @return the list of rooms that match the subject
     */
    public User getUserInfo(User user) {
        if (user == null) {
            throw new IllegalArgumentException("username cannot be null");
        }
        User loginInfo;
        String sql = "SELECT * FROM \"sbdatabase\".\"USER\" u WHERE u.username = ?";
        loginInfo = getFirstResult(sql, new Object[]{user.getUserName()}, new UserRowMapper());

        if (loginInfo == null) {
            loginInfo = new User();
        }
        return loginInfo;
    }

    public List<User> getAllUsers() {
        return getResultSet("SELECT * FROM \"sbdatabase\".\"USER\"", new UserRowMapper());
    }

    public List<Room> getAllRooms() {
        return getResultSet("SELECT * FROM \"sbdatabase\".\"ROOM\"", new RoomRowMapper());
    }

    /**
     * Returns all the rooms that the user has joined.
     * @param user the user in question
     * @return the list of rooms that the user has joined
     */
    public List<Room> getAllRoomsJoinedByUser(User user) {
        //TODO: fix this query
        return getResultSet(
                "SELECT sq.room_id, sq.room_admin, sq.room_name, sq.subject, sq.location, sq.description\n" +
                "FROM (\n" +
                "   SELECT r.room_id, r.room_admin, r.room_name, r.subject, r.location, r.description, ur.username\n" +
                "   FROM \"sbdatabase\".\"ROOM\" r\n" +
                "   INNER JOIN \"sbdatabase\".\"USERROOM\" ur\n" +
                "   ON r.room_id = ur.room_id \n" +
                ") sq\n" +
                "WHERE sq.username = ?; ",
                            new Object[]{user.getUserName()},
                            new RoomRowMapper());
    }

    /**
     * Checks if user passed is admin of the room passed,
     * if so room is removed from DB
     * @param user the user in question
     * @param room the room to be deleted
     * @return 0 if failed, 1 if passed
     */
    public int deleteRoom(User user, Room room) {
        if (room == null) {
            throw new IllegalArgumentException();
        }
        User admin = getRoomAdminInfo(room);
        if (!user.equals(admin)) return 0;

        String sql = "DELETE FROM \"sbdatabase\".\"ROOM\" r WHERE r.room_ID = ?;";
        int result = 0;
        try {
            update(sql, new Object[]{room.getRoomId()});
            result = 1;
        } catch (DataAccessException e) {
            Logger.getLogger(DataController.class.getName()).log(Level.INFO, null, e);
            //e.printStackTrace();
        }
        return result;
    }

    /**
     * Returns all the rooms that match subject string
     * @param subject topic of the room being searched for
     * @return the list of rooms that match the subject
     */
    public List<Room> queryRooms(String subject) {
        Room request = new Room();
        request.setSubject(subject);
        List<Room> resultSet =  getResultSet("SELECT * FROM \"sbdatabase\".\"ROOM\" r WHERE r.subject = ?",
                                    new Object[]{subject},
                                    new RoomRowMapper());
        return resultSet;
    }

    /**
     * Returns the user that admins a room
     * @param room topic of the room being searched for
     * @return the user who is the admin of the room passed
     */
    public User getRoomAdminInfo(Room room) {
        String sql = "SELECT * FROM \"sbdatabase\".\"USER\" u, \"sbdatabase\".\"ROOM\" r " +
                "WHERE r.room_ID = ? AND u.username = r.room_Admin";
        User result = getFirstResult(sql,
                new Object[]{room.getRoomId()},
                new UserRowMapper());
        if (result == null) {
            result = new User();
        }
        return result;
    }

    /**
     * Debugging method, clears Database
     */
    public void clearAllData() {
        //for test set up purposes
        update("DELETE FROM \"sbdatabase\".\"USERROOM\"");
        update("DELETE FROM \"sbdatabase\".\"ROOM\"");
        update("DELETE FROM \"sbdatabase\".\"USER\"");
    }
}
