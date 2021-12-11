import ru.home.usertableapp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://192.168.0.18:5432/test_db" ,"mgr_admin", "31101993");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (connection != null){
            System.out.println("успех");
            System.out.println(connection.toString());
        }else {
            System.out.println("пили еще");
        }

        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users;");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setAge(rs.getInt("age"));
                users.add(user);
                System.out.println(user.getFirstname());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
