package com.example.demo.sbdata;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

/**
 * Contains the methods for testing the StudyBud Data Controller.
 */
class DB_Unit_Test_Driver {

    @Autowired
    private DataController repo;

    /**
     * Tests to see if a user is successfully added to the database. Database should increment user count
     * by one.
     */
    @Test
    void saveUserTest() {
        repo.clearAllData();
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        //User user = null;
        int result = repo.saveUser(user);
        Assert.assertEquals(1, result);
    }

    /**
     * Tests to see if a room is successfully added to the database. Database should increment room count
     * by one.
     */
    @Test
    void saveRoomTest() {
        //repo.clearAllData();
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        Room room = new Room("Chem","Chemistry","FIU","Chem Club!");
        int result = repo.saveRoom(user, room);
        Assert.assertEquals(1, result);
    }

    /**
     * Tests to see if a local user profile matches that of the user profile stored in the database.
     */
    @Test
    void getUserInfoTest() {
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        User resultUser = repo.getUserInfo(user);
        Assert.assertEquals(user, resultUser);
    }

    /**
     * Tests to see if a list of rooms that a user is part of is correct.
     */
    @Test
    void getAllRoomsJoinedByUserTest() {
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        List<Room> roomInfo = repo.getAllRoomsJoinedByUser(user);
        Assert.assertEquals(roomInfo, roomInfo);

    }

    /**
     * Tests to see if a room is successfully removed from the database. Database should be left with
     * one less room.
     */
    @Test
    void deleteRoomTest() {
        //Creates a user
        User user = new User("MickeyMouse",
                "Mickey","Mouse","mickey@gmail.com","passMickey123");
        repo.saveUser(user);

        //Creates a room
        Room room = new Room("DronesBRO","Drones","FIU","This is a drone");
        repo.saveRoom(user,room);

        //Deletes the room
        int result = repo.deleteRoom(user,room);
        Assert.assertEquals(1,result);
    }

    /**
     * Tests to see is a list of rooms matching a String keyword Subject is listed.
     */
    @Test
    void queryRoomsTest() {
        User user = new User("johnny",
                "john","boiii","johnboii@gmail.com","ValidPassword123");
        //Creates a room
        Room room = new Room("SuperDrones","Drones","FIU","This is a drone");

        List<Room> roomInfo = repo.queryRooms("Drones");
        Assert.assertEquals(roomInfo, roomInfo);
    }

    /**
     * Tests to see if a room matches the corresponding admin of the room.
     */
    @Test
    void getRoomAdminInfoTest() {
        User user = new User("johnny",
                "john","boiii","johnboii@gmail.com","ValidPassword123");
        repo.saveUser(user);
        Room room = new Room("SuperDrones","Drones","FIU","This is a drone");
        repo.saveRoom(user, room);

        User resultingUser = repo.getRoomAdminInfo(room);
        Assert.assertEquals(user,resultingUser);
    }

}