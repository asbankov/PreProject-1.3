package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao ud = new UserDaoJDBCImpl();
        ud.saveUser("John", "Lennon", (byte)20);
        System.out.println("User John Lennon added");
        ud.saveUser("Paul", "McCartney", (byte)19);
        System.out.println("User Paul McCartney added");
        ud.saveUser("George", "Harrisson", (byte)20);
        System.out.println("User George Harrisson added");
        ud.saveUser("Ringo", "Star", (byte)20);
        System.out.println("User Ringo Star added");
        List<User> users = ud.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
        ud.cleanUsersTable();
        ud.dropUsersTable();
    }
}
