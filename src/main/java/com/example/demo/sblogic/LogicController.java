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
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LogicController {

    private DataFacade db;              //data package facade
    private Authenticator auth;         //storage of active login sessions

    /**
     * Constructs a new Logic Controller
     * @param db
     * @param auth
     */
    @Autowired
    public LogicController(DataFacade db, Authenticator auth) {
        this.db = db;
        this.auth = auth;
    }

    /**
     * Determines if user is authorized to use StudyBud's services.
     * @param user the user to check
     * @return 1 if authorized, 0 if not authorized
     */
    public int authenticateRequest(User user) {
        return (auth.userIsAuthorized(user));
    }

    /**
     *
     */
    public int verifyLoginRequest(User user) {
        return verifyLogin(user);
    }

    /**
     * Returns the user's stored information from the database. Used for verifying credentials.
     * @param username the user's name in the database
     * @return the user's information, or an empty user if not found.
     */
    public User returnUserInfo(String username) {
        return db.returnUserInfo(username);
    }

    /**
     * Verifies that username and password match with the one in StudyBud's database.
     *
     * @param user user to be tested
     * @return 1 if matches, 0 otherwise
     */
    private int verifyLogin(User user) {
        User dbUser = db.reqLoginInfo(user.getUserName());

        if (dbUser.getUserName() == null) return 0;

        try {
            user.setPassword(hashPass(user.getPassword()));
            if (user.getUserName().equals(dbUser.getUserName())) {
                if (user.getPassword().equals(dbUser.getPassword())) {
                    auth.authorize(user);
                    return 1;
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            Logger.getLogger(LogicController.class.getName()).log(Level.SEVERE, null, e);
            //e.printStackTrace();
        }
        return 0;
    }

    private String hashPass(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return hashPass(password, new byte[]{104,28,39,40,-46,-80,-128,127,49,10});
    }

    /**
     *  Ends the user's current session.
     */
    public void endSessionRequest() {

    }

    /**
     * Removes the user from their current login session.
     * @param user the user to log out
     * @return 1 if successful, 0 if failed
     */
    public int endSession(User user) {
        return auth.removeAuthorization(user);
    }

    /**
     *
     */
    public int requestReg(User user) {
        int ps = this.verifyPass(user.getPassword());
        try {
            if (ps == 1) {
                user.setPassword(hashPass(user.getPassword()));
                return db.requestReg(user);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            Logger.getLogger(LogicController.class.getName()).log(Level.SEVERE, null, e);
        }

        return 0;
    }

    /**
     * Verifies password fits criteria
     * @param pass password to be tested
     * @return 0 if failed, 1 if succeeded
     */
    public int verifyPass(String pass) {
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
     * Returns list of rooms user is a member of
     * @param username user to be tested
     * @return list of rooms
     */
    public List<Room> returnRooms(String username) {
        return db.returnRooms(username);
    }

    /**
     * Creates room with user as admin
     * @param user user to be passed as admin
     * @param room room to be created
     * @return 0 if failed, 1 if succeeded
     */
    public int reqRoomCreate(User user, Room room) {
        return db.createRoom(user, room);
    }

    /**
     * Deletes room if user is admin
     * @param user user to be tested
     * @param room room to be deleted
     * @return 0 if failed, 1 if succeeded
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
     * Verifies that the user has requested a valid page.
     * @param pageToLoad the page to load
     * @return 1 if page is valid, 0 otherwise.
     */
    public int requestPage (String pageToLoad){
        return 1;
    }

    /**
     * Encrypts plaintext password
     * @param passwordToHash plaintext password
     * @param salt byte array for encryption algorithm
     * @return string of encrypted password
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
