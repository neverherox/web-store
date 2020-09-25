package model.dao.implementation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.dao.abtract.AbstractDao;
import model.entity.Catalog;
import model.entity.Product;


public class ProductDao extends AbstractDao<Product> {

    public ProductDao(Connection conn) {
        super(conn);
    }

    public List<Product> getAll() {

        List<Product> products = new ArrayList<Product>();
        try(Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                Catalog catalog = new Catalog();
                catalog.setId(resultSet.getInt(5));
                product.setCatalog(catalog);
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setPrice(resultSet.getDouble(4));
                product.setImage(resultSet.getString(6));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return products;
    }

    @Override
    public void addEntity(Product entity) {

        String sql = "INSERT INTO product(catalog_id, productName, description, price) Values(?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getCatalog().getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setDouble(4, entity.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }


    @Override
    public void deleteEntity(Product entity) {

        String sql = "DELETE FROM product WHERE id = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }


    @Override
    public void editEntity(Product entity) {

        String sql = "UPDATE product SET productName = ?, description = ?, price = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(3, entity.getPrice());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
    }


    public Product getEntityById(int id) {

        Product product = new Product();
        String sql = "SELECT * FROM product WHERE id = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product.setId(resultSet.getInt(1));
                int catalogId = resultSet.getInt(5);
                Catalog catalog = new Catalog();
                catalog.setId(catalogId);
                product.setCatalog(catalog);
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setPrice(resultSet.getDouble(4));
                product.setImage(resultSet.getString(6));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        }
        return product;
    }
}
