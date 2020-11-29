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
                    user = getEntityById(resultSet.getInt(1));
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
        Order order = new Order();

        String sql = "SELECT * FROM user" +
                " left JOIN userorder ON userorder.id = user.userorder_id" +
                " left JOIN order_product ON order_product.order_id = userorder.id" +
                " left JOIN product on product.id = order_product.product_id" +
                " left JOIN catalog ON catalog.id = product.catalog_id" +
                " WHERE user.id = ?";

        try (Connection conn = getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                    user.setLogin(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setRole(UserRole.valueOf(resultSet.getString(4)));
                    if (user.getRole().equals(UserRole.USER)) {
                        order.setId(resultSet.getInt(6));
                        order.setStatus(OrderStatus.valueOf(resultSet.getString(7)));
                        do {
                            Product product = new Product();
                            product.setId(resultSet.getInt(10));
                            product.setName(resultSet.getString(11));
                            product.setDescription(resultSet.getString(12));
                            product.setPrice(resultSet.getDouble(13));
                            product.setImage(resultSet.getString(15));
                            Catalog catalog = new Catalog();
                            catalog.setId(resultSet.getInt(16));
                            catalog.setName(resultSet.getString(17));
                            product.setCatalog(catalog);
                            order.getProducts().add(product);
                        }
                        while (resultSet.next());
                    }
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        user.setOrder(order);
        return user;
    }
}
