package ru.home.usertableapp.DAO;

import org.postgresql.ds.PGSimpleDataSource;
import ru.home.usertableapp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static PGSimpleDataSource ds;

    static  {
        ds = new PGSimpleDataSource();
        ds.setServerName("192.168.0.18");
        ds.setPortNumber(5432);
        ds.setDatabaseName("test_db");
        ds.setUser("mgr_admin");
        ds.setPassword("31101993");
    }

    public UserDAO() {
    }

    private static final String INSERT_USERS_SQL = "INSERT INTO users  (firstname, lastname, age) VALUES  (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id,firstname,lastname,age FROM users WHERE id =?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET firstname = ?, lastname = ?, age =? WHERE id = ?;";


    public void addUser(User user) {
        try(Connection connection = ds.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            PreparedStatement preparedStatement = connection
                    .prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int Id) {
        try(Connection connection = ds.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_USERS_SQL);
            preparedStatement.setInt(1, Id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try(Connection connection = ds.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = ds.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_USERS);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setAge(rs.getInt("age"));
                users.add(user);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int Id) {
        User user = new User();
        try(Connection connection = ds.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement preparedStatement = connection.
                    prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, Id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setAge(rs.getInt("age"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
