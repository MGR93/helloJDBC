package ru.home.usertableapp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbUtil {

    public static Connection getConnection() {
        Connection connection = null;

            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://192.168.0.18:5432/test_db" ,"mgr_admin", "31101993");

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        return connection;

    }
}
