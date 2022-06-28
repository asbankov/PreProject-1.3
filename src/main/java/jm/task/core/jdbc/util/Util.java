package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {

    private static Connection connection = null;
    private final static String driver = "com.mysql.cj.jdbc.Driver";
    private final static String url = "jdbc:mysql://localhost:3306";
    private final static String schemaName = "/pre_proj_schema";
    private final static String user = "root";
    private final static String password = "Duplenkova1430849";
    private final static String dialect = "org.hibernate.dialect.MySQL5Dialect";

    private static SessionFactory sessionFactory;

    public static Connection getConnection () throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
        //return connection.createStatement();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration();

                Properties settings = new Properties();

                settings.put(Environment.DRIVER, driver);
                settings.put(Environment.URL, url + schemaName);
                settings.put(Environment.USER, user);
                settings.put(Environment.PASS, password);
                settings.put(Environment.DIALECT, dialect);

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return sessionFactory;
    }
}
