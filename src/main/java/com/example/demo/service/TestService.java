package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.sbdata.DataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final DataController db;

    @Autowired
    public TestService(DataController db) {
        this.db = db;
    }

    //public List<User> getAllUsers() {
        //return userDao.selectAllUsers();
    //}


    public String login(User user) {
        return "";
    }
}