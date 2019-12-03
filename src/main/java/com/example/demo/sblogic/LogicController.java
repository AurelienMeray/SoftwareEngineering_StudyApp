package com.example.demo.sblogic;

import com.example.demo.sbdata.DataFacade;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component //singleton stereotype for spring boot
public class LogicController {

    //singleton setup
    //private static LogicController instance;

    //data package facade
    private DataFacade db;

    /**
     * Default constructor
     */
    @Autowired
    public LogicController(DataFacade db) {
        this.db = db;

    }

//    public static LogicController getInstance(){
//        if(instance == null){
//            instance = new LogicController();
//        }
//        return instance;
//    }


    /**
     *
     */
    public void requestPage() {
        // TODO implement here
        // sequence diagram
    }

    /**
     *
     */
    public int verifyLoginRequest(User user) {
        // TODO implement here
        return verifyLogin(user);

    }

    /**
     *
     */
    public void returnUserInfo() {
        // TODO implement here
    }

    /**
     *
     */
    private int verifyLogin(User user) {
        // TODO implement here
        User dbuser = db.reqLoginInfo(user.getUserName());
        user.getPassword().equals(dbuser.getPassword());
        if(user.getUserName().equals(dbuser.getUserName())){
            if(user.getPassword().equals(dbuser.getPassword())){
                return 1;
            }
        }
        return 0;
    }

    /**
     *
     */
    public void endSessionRequest() {
        // TODO implement here
    }

    /**
     *
     */
    public void endSession() {
        // TODO implement here
    }

    /**
     *
     */
    public int requestReg(User user) {
        // TODO implement here
        //db.clearAllData(); //for debugging remove later

        int ps = this.verifyPass(user.getPassword());


        if(ps == 1){
            return db.requestReg(user);
        }

        return 0;
    }

    /**
     *
     */
//    public void confirmReg() {
//        // TODO implement here
//    }

    /**
     *
     */
    public int verifyPass(String pass) {

        //string test pass
        //if password valid return 1
        if (pass.length() >= 8){
            // TODO check if capital letter exists
            return 1;
        }
        return 0;
    }

    /**
     *
     */
    public List<Room> requestSearch(String subject) {
        // TODO implement here
        return db.requestRooms(subject);
        //instance.returnRooms();
    }

    /**
     *
     */
    public void returnRooms() {
        // TODO implement here
    }

    /**
     *
     */
    public int reqRoomCreate(User user, Room room) {

        return db.createRoom(user, room);
        //instance.confirmRoom();
        //instance.generateRoomID();
    }

    /**
     *
     */
    public void generateRoomID() {
        // TODO implement here
    }

    /**
     *
     */
    public void confirmRoom() {
        // TODO implement here
        //this.reqRoomCreate();
    }

    /**
     *
     */
    public int deleteRoomReq(User user, Room room) {

        return db.deleteRoom(user, room);

    }

    /**
     *
     */
//    public void confirmDelete() {
//        // TODO implement here
//    }

    /**
     *
     */
    public int joinRoomReq(User user, Room room) {
        // TODO implement here
        return db.connectUserRoom(user, room);
    }

    /**
     *
     */
//    public void confirmJoin() {
//        // TODO implement here
//    }

    /**
     *
     */
    public void hashPass() {
        // TODO implement here
    }

}
