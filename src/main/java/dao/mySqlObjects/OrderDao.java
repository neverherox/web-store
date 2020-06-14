package dao.mySqlObjects;

import dao.interfaces.IOrderDao;
import entity.Order;
import entity.OrderStatus;

import java.sql.*;
import java.util.ArrayList;

public class OrderDao implements IOrderDao {
    private static String url = "jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Moscow&useSSL=false";
    private static String dbUsername = "root";
    private static String dbPass = "1234";

    public ArrayList<Order> GetOrders()
    {
        ArrayList<Order> orders = new ArrayList<Order>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user_order");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    int userId = resultSet.getInt(2);
                    int productId = resultSet.getInt(3);
                    OrderStatus status = OrderStatus.valueOf(resultSet.getString(4));
                    Order order = new Order(id,userId,productId,status);
                    orders.add(order);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return orders;
    }

    public void AddOrder(Order order) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                String sql = "INSERT INTO user_order(user_id, product_id, status) Values(?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, order.getUserId());
                    preparedStatement.setInt(2, order.getProductId());
                    preparedStatement.setString(3, order.getStatus().toString());
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public ArrayList<Order> GetOrders(int userId)
    {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)) {
                String sql = "SELECT * FROM user_order WHERE user_id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, userId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        int id = resultSet.getInt(1);
                        int user_id =  resultSet.getInt(2);
                        int product_id =  resultSet.getInt(3);
                        OrderStatus status = OrderStatus.valueOf(resultSet.getString(4));
                        Order order = new Order(id,user_id,product_id,status);
                        orders.add(order);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return orders;
    }

    public void DeleteOrder(Order order)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                String sql = "DELETE FROM user_order WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, order.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void EditOrder(Order order)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                String sql = "UPDATE user_order SET status = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1,order.getStatus().toString());
                    preparedStatement.setInt(2, order.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public Order GetOrder(int id)
    {
        Order order = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)) {
                String sql = "SELECT * FROM user_order WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int order_id = resultSet.getInt(1);
                        int user_id = resultSet.getInt(2);
                        int product_id = resultSet.getInt(3);
                        OrderStatus status = OrderStatus.valueOf(resultSet.getString(4));
                        order = new Order(order_id, user_id,product_id,status);
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return order;
    }
}
