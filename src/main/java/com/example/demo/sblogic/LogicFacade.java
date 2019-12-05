package com.example.demo.sblogic;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/studybud")
@RestController
public class LogicFacade {

    private LogicController controller;

    /**
     * constructor
     */
    @Autowired
    public LogicFacade(LogicController controller) {
        this.controller = controller;

    }

    /**
     *
     */
    public int requestLoginPage() {
        return controller.requestPage("loginuser");
    }

    public User getUserInfo(String username) {
        return controller.returnUserInfo(username);
    }


    /**
     *
     */
    @PostMapping(path = "login")
    public int verifyLoginRequest(@Valid @NonNull @RequestBody User user) {
        // TODO implement here
        return controller.verifyLoginRequest(user);
    }

    /**
     *
     */
    @GetMapping(path = "{username}/login")
    public User verifiedUser(@PathVariable ("username") String username){
        return controller.returnUserInfo(username);
    }

    /**
     *
     */
    public void endSessionRequest() {
        // TODO implement here
        controller.endSessionRequest();
        //if request valid
        controller.endSession();
    }

    /**
     *
     */
    @GetMapping(path = "register")
    public int requestRegPage() {
        // TODO implement here
        //react handles page?
        return controller.requestPage("createuser");
    }

    /**
     *
     */
    @PostMapping (path = "register")
    public int requestReg(@Valid @NonNull @RequestBody User user) {
        return controller.requestReg(user);
    }

    /**
     *
     */
    @GetMapping(path = "SearchRoom")
    public int requestSearchPage() {
        return controller.requestPage("searchrooms");
        // react implements pages?
        // I am guessing they check to see if the user is authorized to view that page?
    }

    /**
     *
     */
    @GetMapping (path = "SearchRoom/{subject}")
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
    @GetMapping(path = "{username}/Dashboard")
    public List<Room> returnRooms(@PathVariable ("username") String username) {

        return controller.returnRooms(username);
    }

    /**
     *
     */
    @PostMapping (path = "{username}/CreateRoom")
    public int requestRoomCreate(@PathVariable ("username") String username,
                                  @Valid @NonNull @RequestBody Room room) {

        User admin = new User();
        admin.setUserName(username);
        return controller.reqRoomCreate(admin, room);
    }

    /**
     *
     */
    @DeleteMapping (path = "{username}/{roomID}/DeleteRoom")
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
    @PostMapping (path = "{username}/{roomID}/JoinRoom")
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
