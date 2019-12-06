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

class DB_Unit_Test_Driver {

    @Autowired
    private DataController repo;


    @Test
    void test_saveUser() {
        repo.clearAllData();
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        //User user = null;
        int result = repo.saveUser(user);
        Assert.assertEquals(1, result);
    }

    @Test
    void test_saveRoom() {
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        Room room = new Room("Chem","Chemistry","FIU","Chem Club!");
        int result = repo.saveRoom(user, room);
        Assert.assertEquals(1, result);
    }

    @Test
    void test_getUserInfo() {
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        User resultUser = repo.getUserInfo(user);
        Assert.assertEquals(user, resultUser);
    }

    @Test
    void test_getAllRoomsJoinedByUser() {
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        List<Room> roomInfo = repo.getAllRoomsJoinedByUser(user);
        Assert.assertEquals(roomInfo, roomInfo);

    }

    @Test
    void test_deleteRoom() {
        //Creates a user
        User user = new User("MickeyMouse",
                "Mickey","Mouse","mickey@gmail.com","passMickey123");
        repo.saveUser(user);

        //Creates a room
        Room room = new Room("DronesBRO","Drones","FIU","This is a drone");

        //Deletes the room
        repo.saveRoom(user,room);
        int result = repo.deleteRoom(user,room);
        Assert.assertEquals(1,result);
    }

    @Test
    void test_queryRooms() {
        User user = new User("johnny",
                "john","boiii","johnboii@gmail.com","ValidPassword123");
        //Creates a room
        Room room = new Room("SuperDrones","Drones","FIU","This is a drone");

        List<Room> roomInfo = repo.queryRooms("Drones");
        Assert.assertEquals(roomInfo, roomInfo);
    }

    @Test
    void getRoomAdminInfo() {
        User user = new User("johnny",
                "john","boiii","johnboii@gmail.com","ValidPassword123");
        repo.saveUser(user);
        Room room = new Room("SuperDrones","Drones","FIU","This is a drone");
        repo.saveRoom(user, room);

        User resultingUser = repo.getRoomAdminInfo(room);
        Assert.assertEquals(user,resultingUser);
    }

}