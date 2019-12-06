package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  Entity class for User. Users have a username, first name, last name,
 *  email, and password. Users serve as storage of user information that
 *  is saved to StudyBud's database or returned as a response.
 */
public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    /**
     * Constructs a user.
     * @param userName  the user's name
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param email     the user's email
     * @param password  the user's password
     */
    public User(@JsonProperty("username")String userName,
                @JsonProperty("firstname")String firstName,
                @JsonProperty("lastname")String lastName,
                @JsonProperty("email")String email,
                @JsonProperty("password")String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructs a user with null attributes.
     * POSTCONDITION: All attributes are set to null.
     */
    public User() {}

    /**
     * Determines if the other user is the same as this one.
     * Two users are the same if
     *      1) They are the same class.
     *      2) They share the same username (case sensitive).
     * @param other the other user to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) return false;
        User otherUser = (User) other;
        boolean equals = this.userName.equals(otherUser.userName);
        return equals;
    }
    /**
     * Gets the user's username.
     * @return the user's username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's username.
     * @param userName the new user's username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the user's first name.
     * @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     * @param firstName the user's new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     * @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     * @param lastName the user's new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's email address.
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address
     * @param email the user's new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * @param password the user's new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
