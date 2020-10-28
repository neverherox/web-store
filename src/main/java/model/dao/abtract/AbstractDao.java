package model.dao.abtract;

import model.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
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
}
