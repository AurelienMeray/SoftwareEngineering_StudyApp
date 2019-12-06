package com.example.demo.sblogic;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.sbdata.DataController;
import com.example.demo.sbdata.DataFacade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class Logic_Unit_Test_Driver {

    @Autowired
    private LogicController logicCont;

    @Autowired
    private DataController repo;

    @Autowired
    private DataFacade service;

    @Test
    public void authenticateRequestTest(){
        User user = new User("Joejoe",
                "Joe","Boe","JoeBoe@gmail.com","JoeisaBoe123");
        repo.saveUser(user);
        int result = logicCont.authenticateRequest(user);
        Assert.assertEquals(1, result);
    }

    @Test
    public void verifyLoginRequestTest() {
        //repo.clearAllData();
        User user = new User("Tester123",
                "Bob","Stein","Bob123@gmail.com","ValidPass1");
        repo.saveUser(user);
        int result = logicCont.verifyLoginRequest(user);
        Assert.assertEquals(1, result);
    }


    @Test
    public void returnUserInfoTest() {
        //repo.clearAllData();
        User user = new User("Aurelien",
                "Aurelien","Meray","Bob123@gmail.com","ValidPass1");
        repo.saveUser(user);
        User resultingUser = service.returnUserInfo("Aurelien");
        Assert.assertEquals(user, resultingUser);

    }


    @Test
    public void requestRegTest() {
        User user = new User("Aurelien",
                "Aurelien","Meray","Bob123@gmail.com","ValidPass1");
        repo.saveUser(user);
        int result = logicCont.requestReg(user);
        Assert.assertEquals(1,result);
    }

    @Test
    public void verifyPassTest1() {
        String testPass = "AurelienIsTheBest123";
        int result = logicCont.verifyPass(testPass);
        Assert.assertEquals(1, result);
    }

    @Test
    public void verifyPassTest2() {
        String testPass = "notvalid";
        int result = logicCont.verifyPass(testPass);
        Assert.assertEquals(0, result);
    }

    @Test
    public void reqRoomCreate() {
        repo.clearAllData();
        //Creates a user
        User user = new User("MickeyMouse",
                "Mickey","Mouse","mickey@gmail.com","passMickey123");
        repo.saveUser(user);

        Room room = new Room("DronesBRO","Drones","FIU","This is a drone");
        int result = logicCont.reqRoomCreate(user, room);
        Assert.assertEquals(1, result);

    }

    @Test
    public void deleteRoomReq() {
        User user = new User("MickeyMouse",
                "Mickey","Mouse","mickey@gmail.com","passMickey123");
        Room room = new Room("DronesBRO","Drones","FIU","This is a drone");
        int result = logicCont.deleteRoomReq(user, room);
        Assert.assertEquals(1, result);
    }

    @Test
    public void joinRoomReq() {
        User user1 = new User("MickeyMouse",
                "Mickey","Mouse","mickey@gmail.com","passMickey123");
        User user = new User("JohnSmith",
                "John","Smith","johnSmith@gmail.com","johnSmithPass123");
        repo.saveUser(user);
        Room room = new Room("DronesBRO","Drones","FIU","This is a drone");
        int result = logicCont.joinRoomReq(user1, room);
        Assert.assertEquals(1, result);
    }
}