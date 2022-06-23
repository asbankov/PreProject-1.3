package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDaoJDBCImpl implements UserDao {

    private Statement s;

    public UserDaoJDBCImpl() {
        try {
            s = Util.getStatement();
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            //ignore
        }
    }

    public void createUsersTable() {
        dropUsersTable();
        String sql = "CREATE TABLE pre_proj_schema.users (ID BIGINT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(45)," +
                " Lastname VARCHAR(45), Age TINYINT unsigned)";
        try {
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // ignore
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE pre_proj_schema.users";
        try {
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // ignore
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        createUsersTable();
        String sql = "INSERT INTO pre_proj_schema.users (Name, Lastname, Age)" +
                " values('" + name + "', '" + lastName + "', '" + age + "')";
        try {
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // ignore
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE from pre_proj_schema.users where id=" + id;
        try {
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // ignore
        }
    }

    public List<User> getAllUsers() {
        List<User> res = new ArrayList<>();
        String sql = "SELECT * FROM pre_proj_schema.users";
        try {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                res.add(new User(rs.getString("Name"),
                        rs.getString("Lastname"), rs.getByte("Age")));
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // ignore
        }
        return res;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE pre_proj_schema.users";
        try {
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // ignore
        }
    }
}
