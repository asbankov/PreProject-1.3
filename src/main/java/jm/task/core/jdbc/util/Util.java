package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static Connection connection = null;
    private final static String url = "jdbc:mysql://localhost:3306";
    private final static String user = "root";
    private final static String password = "Duplenkova1430849";


    public static Connection getConnection () throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
        //return connection.createStatement();
    }
}
