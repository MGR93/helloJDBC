package ru.home.usertableapp.DAO;

import org.postgresql.Driver;
import ru.home.usertableapp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String jdbcURL = "jdbc:postgresql://192.168.0.18:5432/test_db";
    private String jdbcUsername = "mgr_admin";
    private String jdbcPassword = "31101993";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (firstname, lastname, age) VALUES " +
            " (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id,firstname,lastname,age FROM users WHERE id =?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET firstname = ?, lastname = ?, age =? WHERE id = ?;";

    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(INSERT_USERS_SQL);
            // Parameters start with 1
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setInt(3, user.getAge());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(DELETE_USERS_SQL);
            // Parameters start with 1
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = getConnection()
                    .prepareStatement(UPDATE_USERS_SQL);
            // Parameters start with 1
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_USERS);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("userid"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setAge(rs.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = getConnection().
                    prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("userid"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
