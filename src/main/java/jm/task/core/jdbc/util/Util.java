package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.Properties;

public class Util {

    public static Connection getConnection() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.0.15:3306/test1";
        Properties props = new Properties();
        props.setProperty("user", "kata");
        props.setProperty("password", "kata");

        try {
            Class.forName(driver);
            try {
                return DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                return null;
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
