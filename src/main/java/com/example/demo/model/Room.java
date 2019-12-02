package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Room {
    private UUID roomId;
    private String roomAdmin;
    private String roomName;
    private String subject;
    private String location;
    private String description;

    public Room(@JsonProperty("name")String roomName,
                @JsonProperty("subject")String subject,
                @JsonProperty("location")String location,
                @JsonProperty("description")String description) {
        this.roomId = UUID.randomUUID();
        this.roomName = roomName;
        this.subject = subject;
        this.location = location;
        this.description = description;
    }

    public Room() {
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public void setRoomId(String roomId) {
        UUID roomIDUUID = UUID.fromString(roomId);
        this.roomId = roomIDUUID;
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
