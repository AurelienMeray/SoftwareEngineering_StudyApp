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
        room.setRoomId(rs.getString(1));
        room.setRoomAdmin(rs.getString(2));
        room.setRoomName(rs.getString(3));
        room.setSubject(rs.getString(4));
        room.setLocation(rs.getString(5));
        room.setDescription(rs.getString(6));
        return room;
    }
}
