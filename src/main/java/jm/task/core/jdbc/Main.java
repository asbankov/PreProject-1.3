package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService ud = new UserServiceImpl();
        ud.createUsersTable();
        ud.saveUser("Paul", "McCartney", (byte)19);
        ud.saveUser("John", "Lennon", (byte)20);
        ud.saveUser("George", "Harrisson", (byte)20);
        ud.saveUser("Ringo", "Star", (byte)20);
        List<User> users = ud.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
        ud.cleanUsersTable();
        ud.dropUsersTable();
    }
}
