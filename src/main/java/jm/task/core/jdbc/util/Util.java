package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static Connection c = null;

    public static Statement getStatement () throws SQLException {
        if (c == null) {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306",
                    "root", "Duplenkova1430849");
        }
        return c.createStatement();
    }
}
