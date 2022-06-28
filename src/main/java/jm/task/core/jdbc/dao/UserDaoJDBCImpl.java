package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS pre_proj_schema.users (ID BIGINT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(45)," +
                " Lastname VARCHAR(45), Age TINYINT unsigned)";
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());
            // ignore
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS pre_proj_schema.users";
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // ignore
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO pre_proj_schema.users (Name, Lastname, Age) Values (?, ?, ?)";
        try {
            Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // ignore
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE from pre_proj_schema.users where id = ?";
        try {
            Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // ignore
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM pre_proj_schema.users";
        try {
            Connection connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new User(resultSet.getString("Name"),
                        resultSet.getString("Lastname"), resultSet.getByte("Age")));
            }
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // ignore
        }
        return result;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE pre_proj_schema.users";
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            // System.out.println(ex.getMessage());
            // ignore
        }
    }
}
