package com.example.demo.sbdata;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.testng.annotations.AfterTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest

/**
 * Contains the methods for testing the StudyBud database subsystem.
 */
public class DB_Subsystem_Test_Driver {

    // The DataFacade that will make calls to the mocked DataController.
    @Autowired
    private DataFacade service;

    // The mocked DataController for the purpose of testing.
    @MockBean
    private DataController repo;

    /**
     * Tests to see if the creation of a new user is successful, incrementing the database list of users by
     * one.
     **/
    @Test
    public void saveUserTest(){
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        when(repo.saveUser(user)).thenReturn(1);
        assertEquals(1,service.requestReg(user));
    }

    /**
     * Tests to see if the request for login information is successful, accessing the database to return
     * the login information for the user with the username that was specified, for verifying a login attempt.
     **/
    @Test
    public void getLoginInfoTest(){
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        service.requestReg(user);
        when(repo.getUserInfo(user)).thenReturn(user);
        assertEquals(user,service.reqLoginInfo("Bob123"));
    }

    /**
     * Tests to see if the creating of a room is successful, updating the database with one more room that
     * is under the ownership of the user that chose to create the room.
     **/
    @Test
    public void saveRoomTest(){
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        Room room = new Room("Chem","Chemistry","FIU","Chem Club!");
        when(repo.saveRoom(user,room)).thenReturn(1);
        assertEquals(1,service.createRoom(user,room));
    }

    /**
     * Tests to see if the deletion of a room is successful, updating the database to reflect one less room.
     **/
    @Test
    public void deleteRoomTest(){
        User user = new User("Bob123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        Room room = new Room("Chem","Chemistry","FIU","Chem Club!");
        when(repo.deleteRoom(user,room)).thenReturn(1);
        assertEquals(1,service.deleteRoom(user,room));
    }

    /**
     * Tests to see if a user can be successfully added to the list of users of the room after joining, updating
     * the database to reflect one more user in the room.
     **/
    @Test
    public void connectToRoomTest(){
        User user = new User("Steph123",
                "Steph","Stein","Steph123@gmail.com","ValidPass1");
        Room room = new Room("Calc","Calculus","FIU","Calc Club!");
        when(repo.updateUserRooms(user,room)).thenReturn(1);
        assertEquals(1,service.connectUserRoom(user,room));

    }

    /**
     * Tests to see if the query for a rooms based on a certain subject is successful, accessing the database
     * and returning a list of relevant rooms.
     **/
    @Test
    public void getRoomsTest(){
        List<Room> list = new ArrayList<Room>();
        Room room1 = new Room("Organic Chem","Chemistry","FIU","Chem Club!");
        Room room2 = new Room("Chem 1","Chemistry","FIU","Chem Club!");
        Room room3 = new Room("Chem 2","Chemistry","FIU","Chem Club!");
        list.add(room1);
        list.add(room2);
        list.add(room3);
        when(repo.queryRooms("Chemistry")).thenReturn(list);
        assertEquals(list.size(),service.requestRooms("Chemistry").size());
    }

}
