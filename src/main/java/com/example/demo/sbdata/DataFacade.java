package com.example.demo.sbdata;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DataFacade is a service that performs database retrieval and update
 * requests from StudyBud's database.
 */
@Service
public class DataFacade {
    DataController db;

    /**
     * Default constructor
     */
    @Autowired
    public DataFacade(DataController db) {
        this.db = db;
    }

    /**
     * Obtains a user's login information.
     * @param userName the username
     * @return the user's information
     */
    public User reqLoginInfo(String userName) {
        User request = new User();
        request.setUserName(userName);
        User loginInfo = db.getUserInfo(request);
        return loginInfo;
    }

    /**
     * Adds a user to the member list.
     * @param user the user to add
     * @return a 1 if succeeded, or 0 if failed (user already exists)
     */
    public int requestReg(User user) {
        int result = db.saveUser(user);
        return result;
    }

    /**
     * Returns the rooms of a user specified subject
     * @param subject
     * @return
     */
    public List<Room> requestRooms(String subject) {
        List<Room> rooms = db.queryRooms(subject);
        return rooms;
    }

    /**
     * Adds a room to StudyBud's database and sets the administrator of the
     * newly added room.
     * @param admin the administrator of the created room
     * @param room the room to create
     * @return 1 if succeeded, 0 if failed (
     */
    public int createRoom(User admin, Room room) {
        int result = db.saveRoom(admin, room);
        return result;
    }

    /**
     * Deletes a room from Studybud's database. Note that only the room's
     * administrator can delete a room. If the user is not an administrator
     * of the room, then the room will not be deleted.
     * @param user the administrator of the room
     * @param room
     * @return 1 if succeeded, 0 if failed (room does not exist or user is not an admin)
     */
    public int deleteRoom(User user, Room room) {
        int result = db.deleteRoom(user, room);
        return result;
    }

    /**
     * Joins a user to a room.
     * @param user the user that join the room
     * @param room the room to be joined
     * @return 1 if succeeded, 0 if failed (room does not exist or user already joined)
     */
    public int connectUserRoom(User user, Room room) {
        int result = db.updateUserRooms(user, room);
        return result;
    }

    /**
     * Returns the user's information as a user object. This includes
     * the user's password.
     * @param username the user's username
     * @return the user's information from StudyBud's database
     */
    public User returnUserInfo(String username) {
        User user = new User();
        user.setFirstName(username);
        User fullUser = db.getUserInfo(user);
        return fullUser;
    }

    /**
     * Returns the rooms that the user has joined.
     * @param username the user's username
     * @return the rooms joined by the user
     */
    public List<Room> returnRooms(String username) {
        User user = new User();
        user.setUserName(username);
        return db.getAllRoomsJoinedByUser(user);
    }

    /*
     * Clears everything from StudyBud's database. For testing purposes only.
     */
    public void clearAllData() {
        //db.clearAllData();
    }

}
