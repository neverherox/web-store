package model.dao.implementation;

import model.dao.abtract.AbstractDao;
import model.entity.Order;
import model.entity.OrderStatus;
import model.entity.User;
import model.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class UserDao extends AbstractDao<User> {

    public UserDao(Connection conn) {
        super(conn);
    }

    public User getUser(String login, String password) {
        User user = new User();
        String sql = "SELECT * FROM user JOIN userorder ON userorder.id = user.userorder_id WHERE user.login = ? AND user.password = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(UserRole.valueOf(resultSet.getString(4)));
                Order order = new Order();
                order.setId(resultSet.getInt(6));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(7)));
                user.setOrder(order);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return user;
    }


    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        try(Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user JOIN userorder ON userorder.id = user.userorder_id");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(UserRole.valueOf(resultSet.getString(4)));
                Order order = new Order();
                order.setId(resultSet.getInt(6));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(7)));
                user.setOrder(order);
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return users;
    }

    @Override
    public void addEntity(User entity) {

        String sql = "INSERT INTO user(login, password, role, userorder_id) Values(?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getRole().toString());
            preparedStatement.setInt(4, entity.getOrder().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }


    @Override
    public void deleteEntity(User entity) {

    }

    @Override
    public void editEntity(User entity) {
        String sql = "UPDATE user SET userorder_id = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getOrder().getId());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }

    public User getEntityById(int id) {
        User user = new User();
        String sql = "SELECT * FROM user JOIN userorder ON userorder.id = user.userorder_id WHERE user.id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(UserRole.valueOf(resultSet.getString(4)));
                Order order = new Order();
                order.setId(resultSet.getInt(6));
                order.setStatus(OrderStatus.valueOf(resultSet.getString(7)));
                user.setOrder(order);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

}
