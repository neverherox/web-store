package model.dao.implementation;

import model.dao.abtract.AbstractDao;
import model.entity.Catalog;
import model.entity.Order;
import model.entity.OrderStatus;
import model.entity.Product;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<Order> {
    public OrderDao(Connection conn) {
        super(conn);
    }

    @Override
    public List<Order> getAll() {
        throw new NotImplementedException();
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
        List<Product> products = new ArrayList<>();


        String sql = "SELECT * FROM order_product" +
                " JOIN userorder ON userorder.id = order_product.order_id" +
                " JOIN product ON product.id = order_product.product_id" +
                " JOIN catalog ON catalog.id = product.catalog_id" +
                " WHERE order_product.order_id = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        order.setId(resultSet.getInt(1));
                        Product product = new Product();
                        product.setId(resultSet.getInt(2));
                        order.setStatus(OrderStatus.valueOf(resultSet.getString(4)));
                        product.setName(resultSet.getString(6));
                        product.setDescription(resultSet.getString(7));
                        product.setPrice(resultSet.getDouble(8));
                        Catalog catalog = new Catalog();
                        catalog.setId(resultSet.getInt(11));
                        catalog.setName(resultSet.getString(12));
                        product.setCatalog(catalog);
                        products.add(product);
                        order.setProducts(products);
                    }
                }
                catch(SQLException e){
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
