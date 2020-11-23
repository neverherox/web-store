package model.dao.implementation;

import model.dao.abtract.AbstractDao;
import model.entity.Catalog;
import model.entity.Order;
import model.entity.OrderStatus;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Order> {

    public List<Order> getEmptyOrders() {
        String sql = "SELECT * FROM userorder";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt(1));
                    order.setStatus(OrderStatus.valueOf(resultSet.getString(2)));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return orders;
    }


    @Override
    public void addEntity(Order entity) {

        String sql = "INSERT INTO userorder(status) Values(?)";

        try (Connection conn = getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, entity.getStatus().toString());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }


    @Override
    public void deleteEntity(Order entity) {
        String sql = "DELETE FROM userorder WHERE id = ?";
        try (Connection conn = getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }


    @Override
    public void editEntity(Order entity) {
        String sql = "UPDATE userorder SET status = ? WHERE id = ?";

        try (Connection conn = getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, entity.getStatus().toString());
                preparedStatement.setInt(2, entity.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }

    public void addProductToOrder(Order order, Product product) {
        String sql = "INSERT INTO order_product(order_id, product_id) Values(?,?)";

        try (Connection conn = getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, product.getId());
                preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }

    @Override
    public List<Order> getAll() {
        String sql = "SELECT * FROM userorder";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = getConnection()) {
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Order order = getEntityById(resultSet.getInt(1));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return orders;
    }

    public Order getEntityById(int id) {

        Order order = new Order();
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM userorder" +
                " JOIN order_product ON order_product.order_id = userorder.id"+
                " JOIN product ON product.id = order_product.product_id" +
                " JOIN catalog ON catalog.id = product.catalog_id" +
                " WHERE userorder.id = ?";

        try (Connection conn = getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    order.setId(resultSet.getInt(1));
                    order.setStatus(OrderStatus.valueOf(resultSet.getString(2)));
                    Product product = new Product();
                    product.setId(resultSet.getInt(5));
                    product.setName(resultSet.getString(6));
                    product.setDescription(resultSet.getString(7));
                    product.setPrice(resultSet.getDouble(8));
                    product.setImage(resultSet.getString(10));
                    Catalog catalog = new Catalog();
                    catalog.setId(resultSet.getInt(11));
                    catalog.setName(resultSet.getString(12));
                    product.setCatalog(catalog);
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        order.setProducts(products);
        return order;
    }


    public void deleteProductFromOrder(Order order, Product product) {
        String sql = "DELETE FROM order_product WHERE order_id = ? AND product_id = ? LIMIT 1";

        try (Connection conn = getConnection()) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql);) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, product.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }

}
