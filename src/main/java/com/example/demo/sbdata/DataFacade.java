package com.example.demo.sbdata;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     *
     */
    public User reqLoginInfo(String userName) {
        User request = new User();
        request.setUserName(userName);
        User loginInfo = db.getUserInfo(request);
        return loginInfo;
    }

    /**
     *
     */
    public int requestReg(User user) {
        int result = db.saveUser(user);
        return result;
    }

    /**
     *
     */
    public List<Room> requestRooms(String subject) {
        List<Room> rooms = db.queryRooms(subject);
        return rooms;
    }

    /**
     *
     */
    public int createRoom(User admin, Room room) {
        int result = db.saveRoom(admin, room);
        return result;
    }

    /**
     *
     */
    public int deleteRoom(User user, Room room) {
        int result = db.deleteRoom(user, room);
        return result;
    }

    /**
     *
     */
    public int connectUserRoom(User user, Room room) {
        int result = db.updateUserRooms(user, room);
        return result;
    }

    public User returnUserInfo(String username) {
        User user = new User();
        user.setFirstName(username);
        User fullUser = db.getUserInfo(user);
        return fullUser;
    }

    public List<Room> returnRooms(String username) {
        User user = new User();
        user.setUserName(username);
        return db.getAllRoomsJoinedByUser(user);
    }

    public void clearAllData() {
        db.clearAllData();
    }

}
