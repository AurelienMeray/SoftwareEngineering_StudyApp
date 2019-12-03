package com.example.demo.sblogic;

import com.example.demo.sbdata.DataFacade;
import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;


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

    /**
     *
     */
    public int requestPage(String toOpen) {
        return 1;
        //TODO: figure out whether the user is authorized at this point
        // sequence diagram
    }

    /**
     *
     */
    public int verifyLoginRequest(User user) {
        return verifyLogin(user);
    }

    /**
     *
     */
    public User returnUserInfo(String username) {
        return db.returnUserInfo(username);
    }

    /**
     *
     */
    private int verifyLogin(User user) {
        User dbUser = db.reqLoginInfo(user.getUserName());

        if (dbUser.getUserName() == null) return 0;

        try {
            user.setPassword(hashPass(user.getPassword()));
            if (user.getUserName().equals(dbUser.getUserName())) {
                if (user.getPassword().equals(dbUser.getPassword())) {
                    return 1;
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("What");
            e.printStackTrace();
        }
        return 0;
    }

    private String hashPass(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return hashPass(password, new byte[]{104,28,39,40,-46,-80,-128,127,49,10});
    }

    /**
     *
     */
    public void endSessionRequest() {

    }

    /**
     *
     */
    public void endSession() {

    }

    /**
     *
     */
    public int requestReg(User user) {
        int ps = this.verifyPass(user.getPassword());
        try {
            if (ps == 1) {
                user.setPassword(hashPass(user.getPassword()));
                System.out.println(user.getPassword());
                return db.requestReg(user);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     *
     */
    public int verifyPass(String pass) {
        //string test pass
        //if password valid return 1
        if (pass.length() > 7 && pass.matches("[a-zA-Z_0-9]*[A-Z]+[a-zA-Z_0-9]*")) {
            return 1;
        }
        return 0;
    }

    /**
     *
     */
    public List<Room> requestSearch(String subject) {
        return db.requestRooms(subject);
    }

    /**
     *  Returns the rooms that the user has joined
     */
    public List<Room> returnRooms(String username) {
        return db.returnRooms(username);
    }

    /**
     *
     */
    public int reqRoomCreate(User user, Room room) {
        return db.createRoom(user, room);
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
    public int joinRoomReq(User user, Room room) {
        return db.connectUserRoom(user, room);
    }


    /**
     *
     */
    private String hashPass(String passwordToHash, byte[] salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
