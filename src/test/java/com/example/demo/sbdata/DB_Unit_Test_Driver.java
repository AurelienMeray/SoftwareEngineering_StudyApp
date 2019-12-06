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
    void getFirstResult() {

    }

    @Test
    void testGetFirstResult() {
    }

    @Test
    void getResultSet() {
    }

    @Test
    void testGetResultSet() {
    }

    @Test
    void update() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void checkForUser() {
    }

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

   /* @Test
    void test_updateUserRooms() {
        User user = new User("MickeyMouse",
                "Mickey","Mouse","mickey@gmail.com","passMickey123");
        repo.saveUser(user);
        //Room room = new Room("DronesBRO","Drones","FIU","This is a drone");
        Room room = new Room("Chem","Chemistry","FIU","Chem Club!");
        int result = repo.updateUserRooms(user, room);
        Assert.assertEquals(1, result);
    } */

    @Test
    void test_getUserInfo() {
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        User resultUser = repo.getUserInfo(user);
        Assert.assertEquals(user, resultUser);
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getAllRooms() {
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
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        Room room = new Room("Chem","Chemistry","FIU","Chem Club!");
        int result = repo.deleteRoom(user,room);
        Assert.assertEquals(1,result);
    }

    @Test
    void queryRooms() {
    }

    @Test
    void getRoomAdminInfo() {
    }

    @Test
    void clearAllData() {
    }
}