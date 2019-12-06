package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 *  Entity class for Room. Rooms have an id, administrator, name,
 *  subject, location, and description. Rooms serve as storage of room information that
 *  is saved to StudyBud's database or returned as a response.
 */
public class Room {
    private UUID roomId;
    private String roomAdmin;
    private String roomName;
    private String subject;
    private String location;
    private String description;

    /**
     * Constructs a room.
     * POSTCONDITION: room id is set to a random UUID.
     *
     * @param roomName      the room's name
     * @param subject       the room's subject
     * @param location      the room's location
     * @param description   the room's description
     */
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

    /**
     * Constructs a room with null attributes.
     * POSTCONDITION: All attributes are set to null.
     */
    public Room() {
    }

    /**
     * Determines if the other room is the same as this one.
     * Two rooms are the same if
     *      1) They are the same class.
     *      2) They share the same room id.
     * @param other the other user to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) return false;
        Room otherRoom = (Room) other;
        boolean equals = this.roomId == otherRoom.roomId;
        return equals;
    }

    /**
     * Gets the room's id.
     * @return the room's id
     */
    public UUID getRoomId() {
        return roomId;
    }

    /**
     * Sets the user's id.
     * @param roomId the new uuid
     */
    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    /**
     * Sets the user's id to the string uuid specified in the parameter.
     * @param roomId the new uuid
     * @throws IllegalArgumentException if the param cannot be converted to a UUID
     */
    public void setRoomId(String roomId) {
        UUID roomIDUUID = UUID.fromString(roomId);
        this.roomId = roomIDUUID;
    }

    /**
     * Get the room administrator's username.
     * @return the room administrator's username
     */
    public String getRoomAdmin() {
        return roomAdmin;
    }

    /**
     * Sets the room administrator's username.
     * @param roomAdmin the new administrator's username
     */
    public void setRoomAdmin(String roomAdmin) {
        this.roomAdmin = roomAdmin;
    }

    /**
     * Gets the room's name.
     * @return the room's name
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets the room's name.
     * @param roomName the room's new name
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * Gets the room's subject
     * @return the room's subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the room's subject
     * @param subject the room's new subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gets the room's location.
     * @return the room's location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the room's location.
     * @param location the room's new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the room's description.
     * @return the room's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the room's description
     * @param description the room's new description
     */
    public void setDescription(String description) {
        this.description = description;
    }


}
