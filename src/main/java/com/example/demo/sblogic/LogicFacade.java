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

    //Instantiate controller with singleton method
    LogicController controller;// = LogicController.getInstance();

    /**
     * Default constructor
     */
    @Autowired
    public LogicFacade(LogicController controller) {
        this.controller = controller;

    }


    /**
     *
     */
    public void requestLoginPage() {
        // TODO implement here
        //controller.requestPage(LoginPage);
        //react frontend already handles pulling up loginpage?
    }

    /**
     *
     */
    public void displayRequest() {
        // TODO implement here
        // sequence diagram
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
    public void endSessionRequest() {
        // TODO implement here
        controller.endSessionRequest();
        //if request valid
        controller.endSession();
    }

    /**
     *
     */
    public void requestRegPage() {
        // TODO implement here
        //react handles page?
        controller.requestPage();
    }

    /**
     *
     */
    @PostMapping (path = "register")
    public int requestReg(@Valid @NonNull @RequestBody User user) {
        // TODO implement here
        return controller.requestReg(user);
    }

    /**
     *
     */
    public void requestSearchPage() {
        // TODO implement here
        // react implements pages?
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
    public void requestCreatePage() {
        // TODO implement here
        // look up sequence diagram
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
    public void hidePass() {
        // TODO implement here
        controller.hashPass();
    }

}
