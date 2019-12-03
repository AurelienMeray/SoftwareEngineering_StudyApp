package com.example.demo.api;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.sbdata.DataFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RequestMapping("/api/studybud")
//@RestController
public class UserController {
    private final DataFacade testService;

    //@Autowired
    public UserController(DataFacade testService) {
        this.testService = testService;
    }

    /*
    @GetMapping(path="{nametest}")
    public User nameTest() {
        String userName = "joeschmoe";
        return testService.reqLoginInfo(userName);
    }
     */

    //@GetMapping(path="{roomtest}")
    public List<Room> userInfoTest() {
        User joe = new User("joeshmoe", "joe", "schmoe", "email1", "password");
        User mac = new User("macintosh", "mac", "intosh", "email2", "password");

        Room joeRoom = new Room("test1", "cs", "location", "desc");
        Room macRoom = new Room("test2", "calc", "location", "desc");
        Room macRoom2 = new Room("test2", "calc", "location", "desc");
        Room macRoom3 = new Room("test2", "bio", "location", "desc");

        User marv = new User("marv", "marvin", "torres", "email3", "password");

        testService.clearAllData();

        int result;

        result = testService.requestReg(joe);

        if (result == 0) return null;

        result = testService.requestReg(mac);

        if (result == 0) return null;

        result = testService.requestReg(marv);

        if (result == 0) return null;

        result = testService.createRoom(joe, joeRoom);

        if (result == 0) return null;

        result = testService.createRoom(mac, macRoom);

        if (result == 0) return null;

        result = testService.createRoom(mac, macRoom2);

        if (result == 0) return null;

        result = testService.createRoom(mac, macRoom3);

        if (result == 0) return null;

        result = testService.connectUserRoom(marv, macRoom3);

        if (result == 0) return null;

        String subject = "calc";

        result = testService.deleteRoom(mac, macRoom2);

        if (result == 0) return null;

        result = testService.deleteRoom(marv, macRoom3);

        if (result == 0) System.out.println("good");

        return testService.requestRooms(subject);
    }

    /*
    @PostMapping(path = "{register}")
    public int AddUser(@Valid @NonNull @RequestBody User user) {
        return testService.requestReg(user);
    }

    */

    /*
    @PostMapping(path = "{room/subject}")
    public List<User> getUsersThatLikeMath(@PathVariable("room/subject") String subject) {
        return testService.requestRooms(room)
    }
    */


}
