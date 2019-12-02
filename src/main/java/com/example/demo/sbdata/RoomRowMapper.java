package com.example.demo.sbdata;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        UserResultSetExtractor extractor = new UserResultSetExtractor();
        return extractor.extractData(rs);
    }
}
