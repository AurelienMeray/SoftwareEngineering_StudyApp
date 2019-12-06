package com.example.demo.sblogic;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class Authenticator {
    Set<String> authorizedUsers;

    public Authenticator() {
        authorizedUsers = new HashSet<>();
    }

    public void authorize(User user) {
        authorizedUsers.add(user.getUserName());
    }

    public int userIsAuthorized(User user) {
        int result = 0;
        if (authorizedUsers.contains(user.getUserName())) {
            result = 1;
        }
        return result;
    }

    public int removeAuthorization(User user) {
        int result = 0;
        if (authorizedUsers.remove(user.getUserName())) {
            result = 1;
        }
        return result;
    }

}
