package dao.mySqlObjects;
import java.sql.*;
import java.util.ArrayList;

import dao.interfaces.IProductDao;
import entity.Product;

public class ProductDao implements IProductDao {
    private static String url = "jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Moscow&useSSL=false";
    private static String dbUsername = "root";
    private static String dbPass = "1234";
    public ArrayList<Product> GetProducts()
    {
        ArrayList<Product> products = new ArrayList<Product>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    int catalogId = resultSet.getInt(5);
                    String name = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    double price = resultSet.getDouble(4);
                    String image = resultSet.getString(6);
                    Product product = new Product(id,name,description,price,catalogId,image);
                    products.add(product);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return products;
    }
    public Product GetProduct(int id) {
        Product product = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)) {
                String sql = "SELECT * FROM product WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int productId = resultSet.getInt(1);
                        String productName = resultSet.getString(2);
                        String productDescription = resultSet.getString(3);
                        double productPrice = resultSet.getDouble(4);
                        int catalogId = resultSet.getInt(5);
                        String image = resultSet.getString(6);
                        product = new Product(productId, productName, productDescription, productPrice, catalogId, image);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return product;
    }
    public void EditProduct(Product product)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                String sql = "UPDATE product SET productName = ?, description = ?, price = ? WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(4, product.getId());
                    preparedStatement.setString(1,product.getName());
                    preparedStatement.setDouble(3, product.getPrice());
                    preparedStatement.setString(2, product.getDescription());
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void DeleteProduct(Product product)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                String sql = "DELETE FROM product WHERE id = ?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, product.getId());
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void AddProduct(Product product)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)){

                String sql = "INSERT INTO product(catalog_id, productName, description, price) Values(?, ?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setInt(1, product.getCatalogId());
                    preparedStatement.setString(2, product.getName());
                    preparedStatement.setString(3, product.getDescription());
                    preparedStatement.setDouble(4, product.getPrice());
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
