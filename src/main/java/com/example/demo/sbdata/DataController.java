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

/**
 * DataController is a data access class that performs database retrieval and update
 * requests using SQL statements on a PostgreSQL database. Note that
 * this class must be updated if the database is changed.
 */
@Repository
public class DataController {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Returns the first result of a select query.
     * @param sql the sql query
     * @param rowMapper a row mapper that extracts objects of a specified type from a result set
     * @param <T> the type of object to extract from the result set
     * @return the first result in the requested type if at least one result returned, null if none returned
     */
    public <T> T getFirstResult(String sql, RowMapper rowMapper) {
        List<T> resultSet = jdbcTemplate.query(sql,
                rowMapper);

        if (resultSet.isEmpty()) {
            return null;
        }

        return resultSet.get(0);
    }

    /**
     * Returns the first result of a select query using custom arguments.
     * @param sql the sql query
     * @param params the arguments of the query
     * @param rowMapper a row mapper that extracts objects of a specified type from a result set
     * @param <T> the type of object to extract from the result set
     * @return the first result in the requested type if at least one result returned, null if none returned
     */
    public <T> T getFirstResult(String sql, Object[] params, RowMapper rowMapper) {
        List<T> resultSet = jdbcTemplate.query(sql,
                params,
                rowMapper);

        if (resultSet.isEmpty()) {
            return null;
        }

        return resultSet.get(0);
    }

    /**
     * Returns the result set of a select query.
     * @param sql the sql query
     * @param rowMapper a row mapper that extracts objects of a specified type from a result set
     * @param <T> the type of object to extract from the result set
     * @return a list of objects of a requested type
     */
    public <T> List<T> getResultSet(String sql, RowMapper rowMapper) {
        List<T> resultSet = jdbcTemplate.query(sql,
                rowMapper);
        return resultSet;
    }

    /**
     * Returns the result set of a select query using custom arguments.
     * @param sql the sql query
     * @param params the arguments of the query
     * @param rowMapper a row mapper that extracts objects of a specified type from a result set
     * @param <T> the type of object to extract from the result set
     * @return a list of objects of a requested type
     */
    public <T> List<T> getResultSet (String sql, Object[] params, RowMapper rowMapper) {
        List<T> resultSet = jdbcTemplate.query(sql,
                params,
                rowMapper);
        return resultSet;
    }

    /**
     * Updates the database using custom arguments. Can be used to add, remove, or update table entries.
     * @param sql the sql query
     * @param params the arguments of the query
     */
    public void update(String sql, Object[] params) {
        jdbcTemplate.update(sql, params);
    }

    /**
     * Updates the database. Can be used to add, remove, or update table entries.
     * @param sql the sql query
     */
    public void update(String sql) {
        jdbcTemplate.update(sql);
    }

    /* public int checkForUser(User user) {
        if (user == null) throw new IllegalArgumentException("user can't be null");
        int result = 0;
        String sql = "SELECT * FROM \"sbdatabase\".\"USER\" u WHERE u.username = ? AND u.password = ?";
        if (getFirstResult(sql,
                new Object[]{user.getUserName(), user.getPassword()}, new UserRowMapper()) != null) {
            result = 1;
        }

        return result;
    } */

    /**
     * Adds a user to the user table.
     * PRECONDITION: user is not null.
     * @param user user to be added
     * @return 0 if failed, 1 if succeeded
     * @throws IllegalArgumentException if user is null
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

    /**
     *  Creates new room affiliated with user
     *  PRECONDITION: user and room are not null
     *  @param user the user that can update the room
     *  @param room the room to add
     *  @return 1 if success, 0 if failed
     *  @throws IllegalArgumentException if user or room is null
     */
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
     *  PRECONDITION: user and room are not null
     *  @param user the user that will be part of the room
     *  @param room the room to add
     *  @return 1 if success, 0 if failed
     *  @throws IllegalArgumentException if user or room is null
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
     * PRECONDITION: user is not null.
     * @param user topic of the room being searched for
     * @return the list of rooms that match the subject
     * @throws IllegalArgumentException if user is null
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

   /* public List<User> getAllUsers() {
        return getResultSet("SELECT * FROM \"sbdatabase\".\"USER\"", new UserRowMapper());
    }

    public List<Room> getAllRooms() {
        return getResultSet("SELECT * FROM \"sbdatabase\".\"ROOM\"", new RoomRowMapper());
    } */

    /**
     * Returns all the rooms that the user has joined.
     * PRECONDITION: user is not null.
     * @param user the user in question
     * @return the list of rooms that the user has joined
     * @throws IllegalArgumentException if user is null
     */
    public List<Room> getAllRoomsJoinedByUser(User user) {
        if (user == null) throw new IllegalArgumentException("user can't be null");
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
     * PRECONDITION: user and room are not null.
     * @param user the user in question
     * @param room the room to be deleted
     * @return 0 if failed, 1 if passed
     * @throws IllegalArgumentException if user or room is null.
     */
    public int deleteRoom(User user, Room room) {
        if (user == null || room == null) {
            throw new IllegalArgumentException("user and room can't be null");
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
     * PRECONDITION: subject is not null
     * @param subject topic of the room being searched for
     * @return the list of rooms that match the subject
     * @throws IllegalArgumentException if subject is null
     */
    public List<Room> queryRooms(String subject) {
        if (subject == null) throw new IllegalArgumentException("subject can't be null");
        Room request = new Room();
        request.setSubject(subject);
        List<Room> resultSet =  getResultSet("SELECT * FROM \"sbdatabase\".\"ROOM\" r WHERE r.subject = ?",
                                    new Object[]{subject},
                                    new RoomRowMapper());
        return resultSet;
    }

    /**
     * Returns the user that admins a room
     * PRECONDITION: room is not null
     * @param room topic of the room being searched for
     * @return the user who is the admin of the room passed
     * @throws IllegalArgumentException if room is null
     */
    public User getRoomAdminInfo(Room room) {
        if (room == null) throw new IllegalArgumentException("room can't be null");
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
