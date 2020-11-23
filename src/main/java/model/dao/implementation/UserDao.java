package model.dao.implementation;

import model.dao.abtract.AbstractDao;
import model.entity.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class UserDao extends AbstractDao<User> {

    public User getUser(String login, String password) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE login = ? AND password = ?";
        try (Connection conn = getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                   user =  getEntityById(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return user;
    }


    public List<User> getAll() {
        String sql = "SELECT  * FROM user";
        List<User> users = new ArrayList<User>();

        try (Connection conn = getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    User user = getEntityById(resultSet.getInt(1));
                    users.add(user);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public void addEntity(User entity) {
        String sql = "INSERT INTO user(login, password, role, userorder_id) Values(?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, entity.getLogin());
                preparedStatement.setString(2, entity.getPassword());
                preparedStatement.setString(3, entity.getRole().toString());
                preparedStatement.setInt(4, entity.getOrder().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }


    @Override
    public void deleteEntity(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public void editEntity(User entity) {
        String sql = "UPDATE user SET userorder_id = ? WHERE id = ?";

        try (Connection conn = getConnection()) {

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, entity.getOrder().getId());
                preparedStatement.setInt(2, entity.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }

    public User getEntityById(int id) {
        User user = new User();
        String sql = "SELECT * FROM user" +
                " JOIN userorder ON userorder.id = user.userorder_id" +
                " WHERE user.id = ?";
        String sql1 = "SELECT * FROM order_product" +
                " JOIN product ON product.id = order_product.product_id" +
                " JOIN catalog ON catalog.id = product.catalog_id" +
                " WHERE order_product.order_id = ?";

        try (Connection conn = getConnection()) {
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
                    try (PreparedStatement preparedStatement1 = conn.prepareStatement(sql1)) {
                        preparedStatement1.setInt(1, order.getId());
                        resultSet = preparedStatement1.executeQuery();
                        while (resultSet.next()) {
                            Product product = new Product();
                            product.setId(resultSet.getInt(3));
                            product.setName(resultSet.getString(4));
                            product.setDescription(resultSet.getString(5));
                            product.setPrice(resultSet.getDouble(6));
                            Catalog catalog = new Catalog();
                            catalog.setId(resultSet.getInt(9));
                            catalog.setName(resultSet.getString(10));
                            product.setImage(resultSet.getString(8));
                            product.setCatalog(catalog);
                            order.getProducts().add(product);
                        }
                    }
                    user.setOrder(order);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

}
