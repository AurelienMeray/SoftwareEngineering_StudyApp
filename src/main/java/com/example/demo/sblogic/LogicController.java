package com.example.demo.sblogic;

import com.example.demo.sbdata.DataFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component //singleton stereotype for spring boot
public class LogicController {

    //singleton setup
    private static LogicController instance;
    private DataFacade db;

    /**
     * Default constructor
     */
    @Autowired
    public LogicController() {

    }

    public static LogicController getInstance(){
        if(instance == null){
            instance = new LogicController();
        }
        return instance;
    }


    /**
     *
     */
    public void requestPage() {
        // TODO implement here
    }

    /**
     *
     */
    public void verifyLoginRequest() {
        // TODO implement here
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
    public void verifyLogin() {
        // TODO implement here
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
    public void requestReg() {
        // TODO implement here
    }

    /**
     *
     */
    public void confirmReg() {
        // TODO implement here
    }

    /**
     *
     */
    public void verifyPass() {
        // TODO implement here
    }

    /**
     *
     */
    public void requestSearch() {
        // TODO implement here
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
    public void reqRoomCreate() {
        // TODO implement here
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
    }

    /**
     *
     */
    public void deleteRoomReq() {
        // TODO implement here
    }

    /**
     *
     */
    public void confirmDelete() {
        // TODO implement here
    }

    /**
     *
     */
    public void joinRoomReq() {
        // TODO implement here
    }

    /**
     *
     */
    public void confirmJoin() {
        // TODO implement here
    }

    /**
     *
     */
    public void hashPass() {
        // TODO implement here
    }

}
