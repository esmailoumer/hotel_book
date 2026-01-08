package test.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.util.DBConnection;

public class Rooms {

    public List<Map<String, Object>> getAllUsers() {
        List<Map<String, Object>> rooms = new ArrayList<>();
        String sql = "SELECT id, room_number, room_type, base_price, status FROM rooms";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> room = new HashMap<>();
                room.put("id", rs.getInt("id"));
                room.put("room_number", rs.getInt("room_number"));
                room.put("room_type", rs.getString("room_type"));
                room.put("base_price", rs.getDouble("base_price"));
                room.put("status", rs.getString("status"));

                rooms.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
