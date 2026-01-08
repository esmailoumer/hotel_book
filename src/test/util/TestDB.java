package test.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " +
                        rs.getString("name") + " - " +
                        rs.getString("email") + " - " +
                        rs.getString("role"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
