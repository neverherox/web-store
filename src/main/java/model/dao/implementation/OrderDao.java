package model.dao.implementation;

import model.dao.abtract.AbstractDao;
import model.entity.Order;
import model.entity.OrderStatus;
import model.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Order> {
    public OrderDao(Connection conn) {
        super(conn);
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM order_product WHERE order_id = ?";
        try(Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userorder");
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(1));

               try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                   preparedStatement.setInt(1, order.getId());
                   ResultSet resultSet1 = preparedStatement.executeQuery();
                   List<Product> products = new ArrayList<>();
                   while (resultSet1.next()) {
                       Product product = new Product();
                       product.setId(resultSet1.getInt(2));
                       products.add(product);
                   }
                   order.setProducts(products);
                   order.setStatus(OrderStatus.valueOf(resultSet.getString(2)));
                   orders.add(order);
               }
               catch (SQLException e)
               {
                   System.err.println("SQL exception (request or table failed): " + e);
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
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, entity.getStatus().toString());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }


    @Override
    public void deleteEntity(Order entity) {

        String sql = "DELETE FROM userorder WHERE id = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }


    @Override
    public void editEntity(Order entity) {

        String sql = "UPDATE userorder SET status = ? WHERE id = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, entity.getStatus().toString());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }

    public void addProductToOrder(Order order, Product product) {
        String sql = "INSERT INTO order_product(order_id, product_id) Values(?,?)";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }

    public Order getEntityById(int id) {

        Order order = new Order();
        String sql = "SELECT * FROM userorder WHERE id = ?";
        String sql1 = "SELECT * FROM order_product WHERE order_id = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            try(PreparedStatement preparedStatement1 = conn.prepareStatement(sql1))
                {
                    preparedStatement.setInt(1, id);
                    preparedStatement1.setInt(1, id);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        order.setId(resultSet.getInt(1));
                        ResultSet resultSet1 = preparedStatement1.executeQuery();
                        List<Product> products = new ArrayList<>();
                        while (resultSet1.next()) {
                            Product product = new Product();
                            product.setId(resultSet1.getInt(2));
                            products.add(product);
                        }
                        order.setProducts(products);
                        order.setStatus(OrderStatus.valueOf(resultSet.getString(2)));
                    }
                }
                catch(SQLException e){
                    System.err.println("SQL exception (request or table failed): " + e);
                }
            } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return order;
    }


    public void deleteProductFromOrder(Order order, Product product) {
        String sql = "DELETE FROM order_product WHERE order_id = ? AND product_id = ? LIMIT 1";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql);) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }

}
