package com.example.demo.sbdata;

import com.example.demo.model.Room;
import com.example.demo.model.User;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomResultSetExtractor implements ResultSetExtractor {
    @Override
    public Object extractData(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setRoomId(rs.getString("room_id"));
        room.setRoomAdmin(rs.getString("room_admin"));
        room.setRoomName(rs.getString("room_name"));
        room.setSubject(rs.getString("subject"));
        room.setLocation(rs.getString("location"));
        room.setDescription(rs.getString("description"));
        return room;
    }
}
