package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String createString =
                "CREATE TABLE Users " + "(id integer NOT NULL AUTO_INCREMENT, " +
                        "name varchar(255) NOT NULL, " + "lastName varchar(255), " +
                        "age integer, " + "PRIMARY KEY (id))";

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(createString);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void dropUsersTable() {

        String dropString = "DROP TABLE Users";

        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(dropString);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String insertString = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, " +
                "?)";

        try (PreparedStatement insertStmt = conn.prepareStatement(insertString)) {
            insertStmt.setString(1, name);
            insertStmt.setString(2, lastName);
            insertStmt.setInt(3, age);
            insertStmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        final List<User> users = new ArrayList<>();
        String selectString = "SELECT * FROM Users";

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(selectString);
            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                String lastName = rs.getString(3);
                byte age = (byte) rs.getInt(4);
                users.add(new User(id, name, lastName, age));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        String deleteString = "DELETE FROM Users";

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(deleteString);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
