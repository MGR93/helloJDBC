package ru.home.usertableapp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Class.forName("org.postgresql.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:postgresql://192.168.0.18:5432/test_db," ,"mgr_admin", "31101993");
                connection.close();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
