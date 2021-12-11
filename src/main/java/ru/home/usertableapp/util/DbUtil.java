package ru.home.usertableapp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbUtil {
    private static Connection connection;

    public static Connection getConnection() {


            try {
                Class.forName("org.postgresql.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:postgresql://192.168.0.18:5432/test_db" ,"mgr_admin", "31101993");

            } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        return connection;

    }
}
