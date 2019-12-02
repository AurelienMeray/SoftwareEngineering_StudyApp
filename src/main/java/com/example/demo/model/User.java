package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

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

    public User() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object other) {
        User otherUser = (User) other;
        boolean equals = this.userName.equals(otherUser.userName);
        return equals;
    }
}
