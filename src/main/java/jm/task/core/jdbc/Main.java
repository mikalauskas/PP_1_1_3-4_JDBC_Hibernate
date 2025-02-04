package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ruslan", "M", (byte) 34);
        userService.saveUser("Oleg", "God", (byte) 45);
        userService.saveUser("Roman", "Michael", (byte) 76);
        userService.saveUser("Tom", "Riddle", (byte) 32);

        List<User> usersList = userService.getAllUsers();
        usersList.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
