package test.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.util.DBConnection;

public class Booking {

    public List<Map<String, Object>> getAllUsers() {
        List<Map<String, Object>> bookings = new ArrayList<>();
        String sql = "SELECT id, user_id, room_id, guests, check_in, check_out, status, total_price, special_request FROM bookings";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> book = new HashMap<>();
                book.put("id", rs.getInt("id"));
                book.put("user_id", rs.getInt("user_id"));
                book.put("room_id", rs.getString("room_id"));
                book.put("guests", rs.getDouble("guests"));
                book.put("check_in", rs.getString("check_in"));
                book.put("check_out", rs.getString("check_out"));
                book.put("status", rs.getString("status"));
                book.put("total_price", rs.getString("total_price"));
                book.put("special_request", rs.getString("special_request"));

                bookings.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
