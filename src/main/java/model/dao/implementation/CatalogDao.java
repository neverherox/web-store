package model.dao.implementation;

import model.dao.abtract.AbstractDao;
import model.entity.Catalog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogDao extends AbstractDao<Catalog> {

    public CatalogDao(Connection conn) {
        super(conn);
    }

    @Override
    public void addEntity(Catalog entity) {

    }

    @Override
    public void deleteEntity(Catalog entity) {

    }

    @Override
    public void editEntity(Catalog entity) {

    }

    @Override
    public List<Catalog> getAll() {
        List<Catalog> catalogs = new ArrayList<Catalog>();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM catalog");
            while (resultSet.next()) {
                Catalog catalog = new Catalog();
                catalog.setId(resultSet.getInt(1));
                catalog.setName(resultSet.getString(2));
                catalogs.add(catalog);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(statement);
        }

        return catalogs;
    }

    @Override
    public Catalog getEntityById(int id) {

        Catalog catalog = new Catalog();
        String sql = "SELECT * FROM catalog WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                catalog.setId(resultSet.getInt(1));
                catalog.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(preparedStatement);
        }
        return catalog;
    }
}
