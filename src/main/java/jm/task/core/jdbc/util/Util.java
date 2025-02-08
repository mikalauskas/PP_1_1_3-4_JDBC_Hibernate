package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

public class Util {
    private final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/kata";
    private final static String DB_USER = "kata";
    private final static String DB_PASSWORD = "kata";
    private static SessionFactory sessionFactory;

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

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties props = new Properties();
            props.setProperty("hibernate.connection.driver_class", JDBC_DRIVER);
            props.setProperty("hibernate.connection.url", DB_URL);
            props.setProperty("hibernate.connection.username", DB_USER);
            props.setProperty("hibernate.connection.password", DB_PASSWORD);

            Configuration configuration = new Configuration();
            configuration.addProperties(props);
            configuration.addAnnotatedClass(User.class);

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
