package com.studybud.studybuddemo.SBLogic;

import java.util.*;

public class SBLogicController {

    //singleton setup
    private static SBLogicController instance;

    /**
     * Default constructor
     */
    public SBLogicController() {

    }

    public static SBLogicController getInstance(){
        if(instance == null){
            instance = new SBLogicController();
        }
        return instance;
    }


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
    public void verifyLoginRequest() {
        // TODO implement here
        instance.verifyLogin();
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
        instance.confirmReg();
        instance.verifyPass();
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
        instance.returnRooms();
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
        instance.confirmRoom();
        instance.generateRoomID();
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
        instance.confirmDelete();
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
