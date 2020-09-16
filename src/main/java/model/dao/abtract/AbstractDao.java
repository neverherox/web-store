package model.dao.abtract;

import model.entity.Entity;
import model.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao<T extends Entity> {
    protected Connection conn;

    public AbstractDao(Connection conn) {
        this.conn = conn;
    }

    public abstract List<T> getAll();

    public abstract T getEntityById(int id);

    public abstract void addEntity(T entity) throws SQLException;

    public abstract void deleteEntity(T entity);

    public abstract void editEntity(T entity);


    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) { // лог о невозможности закрытия Statement } }

        }
    }
}
