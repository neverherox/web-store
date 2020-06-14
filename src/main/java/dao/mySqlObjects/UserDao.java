package dao.mySqlObjects;

import dao.interfaces.IUserDao;
import entity.User;
import entity.UserRole;

import java.sql.*;
import java.util.ArrayList;


public class UserDao implements IUserDao {
    private static String url = "jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Moscow&useSSL=false";
    private static String dbUsername = "root";
    private static String dbPass = "1234";

    public User GetUser(String login, String password) {
        User user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)) {
                String sql = "SELECT * FROM user WHERE login = ? AND password = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, login);
                    preparedStatement.setString(2, password);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int userId = resultSet.getInt(1);
                        String userLogin = resultSet.getString(2);
                        String userPassword = resultSet.getString(3);
                        UserRole userRole = UserRole.valueOf(resultSet.getString(4));
                        double userMoney = resultSet.getDouble(5);
                        user = new User(userId, userLogin, userPassword, userRole, userMoney);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return user;
    }

    public void SetUser(User user)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                String sql = "INSERT INTO user(login, password, role) Values(?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getRole().toString());
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }

    public User GetUser(int id) {
        User user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)) {
                String sql = "SELECT * FROM user WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int userId = resultSet.getInt(1);
                        String userLogin = resultSet.getString(2);
                        String userPassword = resultSet.getString(3);
                        UserRole userRole = UserRole.valueOf(resultSet.getString(4));
                        double money = resultSet.getDouble(5);
                        user = new User(userId, userLogin, userPassword, userRole,money);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return user;
    }
    public ArrayList<User> GetUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
                while(resultSet.next()){

                    int userId = resultSet.getInt(1);
                    String userLogin = resultSet.getString(2);
                    String userPassword = resultSet.getString(3);
                    UserRole userRole = UserRole.valueOf(resultSet.getString(4));
                    double money = resultSet.getDouble(5);
                    User user = new User(userId, userLogin, userPassword, userRole, money);
                    users.add(user);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return users;
    }
    public void EditUser(User user)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                String sql = "UPDATE user SET money= ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setDouble(1,user.getMoney());
                    preparedStatement.setInt(2, user.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
