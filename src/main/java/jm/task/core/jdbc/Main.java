package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John", "Lennon", (byte)20);
        //System.out.println("User John Lennon added");
        userService.saveUser("Paul", "McCartney", (byte)19);
        //System.out.println("User Paul McCartney added");
        userService.saveUser("George", "Harrisson", (byte)20);
        //System.out.println("User George Harrisson added");
        userService.saveUser("Ringo", "Star", (byte)20);
        //System.out.println("User Ringo Star added");
        List<User> users = userService.getAllUsers();
        //System.out.println(users.size());
        for (User u : users) {
            System.out.println(u);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
