package model.service.interfaces;

import model.entity.Catalog;
import model.entity.Order;
import model.entity.Product;
import model.entity.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IService {
    List<Product> getProducts() throws SQLException, NamingException;

    User getUser(String login, String password) throws SQLException, NamingException;

    void setUser(User user) throws SQLException, NamingException;

    Product getProduct(int id) throws SQLException;

    User getUser(int id) throws SQLException;

    List<Catalog> getCatalogs() throws SQLException, NamingException;

    Catalog getCatalog(int id) throws SQLException, NamingException;

    void addOrder(Order order) throws SQLException;

}
