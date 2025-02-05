package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://192.168.0.15:3306/test1";
    private final static String DB_USER = "kata";
    private final static String DB_PASSWORD = "kata";

    public static Connection getConnection() {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.err.format("Connection State: %s\n%s", e.getSQLState(), e.getMessage());
            return null;
        }
    }
}
