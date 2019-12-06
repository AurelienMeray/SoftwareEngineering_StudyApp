package com.example.demo.sblogic;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * An authenticator serves to save session login information and verify that users have logged into StudyBud
 * before using its services.
 *
 * Note that the authenticator saves up to one session per user.
 */
@Component
public class Authenticator {
    Set<String> authorizedUsers; //active login sessions

    /**
     * Instantiates an Authenticator.
     */
    public Authenticator() {
        authorizedUsers = new HashSet<>();
    }

    /**
     * Adds user to the list of authorized users.
     * POSTCONDITION: user is allowed to create rooms, delete rooms, and search rooms.
     * @param user the user to add
     */
    public void authorize(User user) {
        authorizedUsers.add(user.getUserName());
    }

    /**
     * Determines if user is allowed to use StudyBud's services. They are allowed if they are
     * an authorized user.
     * @param user the user to check
     * @return 1 for authorized, 0 for not authorized
     */
    public int userIsAuthorized(User user) {
        int result = 0;
        if (authorizedUsers.contains(user.getUserName())) {
            result = 1;
        }
        return result;
    }

    /**
     * Removes the user from the list of authorized users. Necessary to log out users from StudyBud.
     * @param user the user to remove
     * @return 1 if user is found and removed, 0 otherwise
     */
    public int removeAuthorization(User user) {
        int result = 0;
        if (authorizedUsers.remove(user.getUserName())) {
            result = 1;
        }
        return result;
    }

}
