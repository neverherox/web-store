package model.service.interfaces;

import model.entity.Catalog;
import model.entity.Order;
import model.entity.Product;
import model.entity.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public interface IService {
    List<Product> GetProducts() throws SQLException, NamingException;

    User GetUser(String login, String password) throws SQLException, NamingException;

    void SetUser(User user) throws SQLException, NamingException;

    Product GetProduct(int id) throws SQLException;

    User GetUser(int id) throws SQLException;

    List<Catalog> GetCatalogs() throws SQLException, NamingException;

    Catalog GetCatalog(int id) throws SQLException, NamingException;

    void AddOrder(Order order) throws SQLException;

}
