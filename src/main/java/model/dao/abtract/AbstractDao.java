package model.dao.abtract;

import model.entity.Entity;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T extends Entity> {
    private static DataSource dataSource;

    public AbstractDao() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/store");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public abstract List<T> getAll();

    public abstract T getEntityById(int id);

    public abstract void addEntity(T entity);

    public abstract void deleteEntity(T entity);

    public abstract void editEntity(T entity);

    protected Connection getConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        return conn;
    }
}
