package dao.mySqlObjects;

import dao.interfaces.ICatalogDao;
import entity.Catalog;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;

public class CatalogDao implements ICatalogDao {

    private static String url = "jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Moscow&useSSL=false";
    private static String dbUsername = "root";
    private static String dbPass = "1234";

    public ArrayList<Catalog> GetCatalogs() {
        ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)) {

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM catalog");
                while (resultSet.next()) {

                    int catalogId = resultSet.getInt(1);
                    String catalogName = resultSet.getString(2);
                    Catalog catalog = new Catalog(catalogId, catalogName);
                    catalogs.add(catalog);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return catalogs;
    }
    public Catalog GetCatalog(int id)
    {
       Catalog catalog = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPass)) {
                String sql = "SELECT * FROM catalog WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setInt(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int catalogId = resultSet.getInt(1);
                        String catalogName = resultSet.getString(2);
                        catalog = new Catalog(catalogId,catalogName);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return catalog;
    }
}
