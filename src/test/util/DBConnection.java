package test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";;
    private static final String USER = "root";
    private static final String PASSWORD = "biniyam@1234";

    static { // THIS TOOK ME HOURS TO FIGURE OUT, I HAD TO FORCE THE CONNECTION BETWEEN TOMCAT AND MYSQL, 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);;
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
