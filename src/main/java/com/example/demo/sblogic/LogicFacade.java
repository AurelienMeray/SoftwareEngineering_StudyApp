package com.example.demo.sblogic;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * LogicFacade is a service that dispatches requests from the interface layer without revealing detailed information
 * about the other classes that compose the logic layer.
 */
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/studybud")
@RestController
public class LogicFacade {

    private LogicController controller; //The logic controller

    /**
     * Constructs a LogicFacade.
     * @param controller the logic controller that is injected
     */
    @Autowired
    public LogicFacade(LogicController controller) {
        this.controller = controller;

    }

    /**
     * Determines if the user is allowed to use StudyBud's services. A user is allowed to use StudyBud's
     * services if they have a currently active session.
     * @param user the user to check
     * @return 1 if authorized, 0 if not authorized
     */
    @GetMapping(path = "/isLoggedIn")
    public int authenticateRequest(@Valid @NonNull @RequestBody User user) {
        return controller.authenticateRequest(user);
    }

    /**
     * Verifies the user's credentials
     * @param user the user's information, which contains username and password
     * @return 1 if verified, 0 if rejected
     */
    @PostMapping(path = "/login")
    public int verifyLoginRequest(@Valid @NonNull @RequestBody User user) {
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        return controller.verifyLoginRequest(user);

    }

    /**
     * Returns the user's information.
     * @param username the user to obtain
     * @return the user's information.
     */
    @GetMapping(path = "{username}/login")
    public User verifiedUser(@PathVariable ("username") String username){
        return controller.returnUserInfo(username);
    }

    /**
     *
     */
    @PostMapping(path = "/logout")
    public int endSessionRequest(@Valid @NonNull @RequestBody User user) {
        // TODO implement here
        controller.endSessionRequest();
        //if request valid
        return (controller.endSession(user));
    }

    /**
     *
     */
    @GetMapping(path = "/register")
    public int requestRegPage() {
        // TODO implement here
        //react handles page?
        return controller.requestPage("createuser");
    }

    /**
     *
     */
    @PostMapping (path = "/register")
    public int requestReg(@Valid @NonNull @RequestBody User user) {
        return controller.requestReg(user);
    }

    /**
     *
     */
    @GetMapping(path = "/searchroom")
    public int requestSearchPage() {
        return controller.requestPage("searchrooms");
        // react implements pages?
        // I am guessing they check to see if the user is authorized to view that page?
    }

    /**
     *
     */
    @GetMapping (path = "searchroom/{subject}")
    public List<Room> requestSearch(@PathVariable ("subject") String subject) {
        // TODO implement here
        return controller.requestSearch(subject);

    }

    /**
     *
     */
    @GetMapping(path = "createrooms")
    public int requestCreatePage() {
        return controller.requestPage("createroom");
    }

    /**
     *
     */
    @GetMapping(path = "{username}/dashboard")
    public List<Room> returnRooms(@PathVariable ("username") String username) {

        return controller.returnRooms(username);
    }

    /**
     *
     */
    @PostMapping (path = "{username}/createroom")
    public int requestRoomCreate(@PathVariable ("username") String username,
                                  @Valid @NonNull @RequestBody Room room) {

        User admin = new User();
        admin.setUserName(username);
        return controller.reqRoomCreate(admin, room);
    }

    /**
     *
     */
    @DeleteMapping (path = "{username}/{roomID}/deleteroom")
    public int deleteRoomReq(@PathVariable ("username") String username,
                             @PathVariable ("roomID") UUID roomID) {
        // TODO implement here
        User user = new User();
        user.setUserName(username);
        Room room = new Room();
        room.setRoomId(roomID);
        return controller.deleteRoomReq(user, room);
    }

    /**
     *
     */
    @PostMapping (path = "{username}/{roomID}/joinroom")
    public int joinRoomReq(@PathVariable ("username") String username,
                            @PathVariable ("roomID") UUID roomID) {
        // TODO implement here
        User user = new User();
        user.setUserName(username);
        Room room = new Room();
        room.setRoomId(roomID);
        return controller.joinRoomReq(user, room);
    }

    /**
     *
     */
    private void hidePass() {
        //TODO: figure out what to do here
    }

}
