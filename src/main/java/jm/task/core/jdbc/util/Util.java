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

    private static Connection c = null;

    private static SessionFactory sessionFactory;

    public static Statement getStatement () throws SQLException {
        if (c == null) {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306",
                    "root", "Duplenkova1430849");
        }
        return c.createStatement();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration();

                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/pre_proj_schema");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Duplenkova1430849");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

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
