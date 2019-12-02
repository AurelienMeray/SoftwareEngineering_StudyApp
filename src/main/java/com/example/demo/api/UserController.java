package com.example.demo.api;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import com.example.demo.sbdata.DataFacade;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class UserController {
    private final DataFacade testService;

    @Autowired
    public UserController(DataFacade testService) {
        this.testService = testService;
    }

    @GetMapping(path="{nametest}")
    public User getUserInfo() {
        String userName = "joeschmoe";
        return testService.reqLoginInfo(userName);
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
