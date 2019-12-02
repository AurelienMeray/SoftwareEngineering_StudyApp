package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    //@GetMapping
    //public List<User> getAllUsers() {
    //    return testService.getAllUsers();
    //}

    @PostMapping
    public String login(@Valid @NonNull @RequestBody User user) {
        return testService.login(user);
    }

}
