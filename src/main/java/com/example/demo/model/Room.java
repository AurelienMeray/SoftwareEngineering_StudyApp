package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Room {
    private int roomId;
    private String roomAdmin;
    private String roomName;
    private String subject;
    private String location;
    private String time;
    private String description;

    public Room(@JsonProperty("name")String roomName,
                @JsonProperty("subject")String subject,
                @JsonProperty("location")String location,
                @JsonProperty("time")String time,
                @JsonProperty("description")String description) {
        this.roomName = roomName;
        this.subject = subject;
        this.location = location;
        this.time = time;
        this.description = description;
    }

    public Room() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomAdmin() {
        return roomAdmin;
    }

    public void setRoomAdmin(String roomAdmin) {
        this.roomAdmin = roomAdmin;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object other) {
        Room otherRoom = (Room) other;
        boolean equals = this.roomId == otherRoom.roomId;
        return equals;
    }
}
