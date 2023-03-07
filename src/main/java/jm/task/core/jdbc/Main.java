package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args)  {
        UserService userService = new UserServiceImpl();

        final User user1 = new User("Катя", "Волкова", (byte) 21);
        User user2 = new User("Лена", "Соловьева", (byte) 18);
        User user3 = new User("Даша", "Сон", (byte) 26);
        User user4 = new User("Аня", "Лапина", (byte) 24);

        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.stop();
    }
}