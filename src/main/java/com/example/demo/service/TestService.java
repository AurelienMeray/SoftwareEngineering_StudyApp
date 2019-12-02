package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.sbdata.DataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public int login(User user) {
        return db.checkForUser(user);
    }

    public int addUser(User user) {
        return db.saveUser(user);
    }

    public List<User> getAllUsers() {
        return db.getAllUsers();
    }

}