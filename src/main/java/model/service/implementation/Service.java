package model.service.implementation;

import model.dao.implementation.OrderDao;
import model.dao.pool.ConnectionPool;
import model.dao.implementation.CatalogDao;
import model.dao.implementation.ProductDao;
import model.dao.implementation.UserDao;
import model.entity.Catalog;
import model.entity.Order;
import model.entity.Product;
import model.entity.User;
import model.service.interfaces.IService;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Service implements IService {
    private ConnectionPool pool = new ConnectionPool();


    public List<Product> getProducts() throws SQLException {
        Connection conn = pool.getConnection();

        ProductDao productDao = new ProductDao(conn);
        List<Product> products = productDao.getAll();
        conn.close();
        return products;
    }


    public User getUser(String login, String password) throws SQLException {
        Connection conn = pool.getConnection();

        UserDao userDao = new UserDao(conn);
        OrderDao orderDao = new OrderDao(conn);

        User user = userDao.getUser(login, password);
        user.setOrder(orderDao.getEntityById(user.getOrder().getId()));
        conn.close();
        return user;
    }

    public void setUser(User user) throws SQLException {
        Connection conn = pool.getConnection();
        OrderDao orderDao = new OrderDao(conn);
        UserDao userDao = new UserDao(conn);
        List<Order> orders;
        orders = orderDao.getAll();
        int count = orders.size();
        Order order = orders.get(count - 1);
        user.setOrder(order);
        userDao.addEntity(user);
        conn.close();

    }

    public void addOrder(Order order) throws SQLException {
        Connection conn = pool.getConnection();

        OrderDao orderDao = new OrderDao(conn);
        orderDao.addEntity(order);
        conn.close();
    }

    public Product getProduct(int id) throws SQLException {
        Connection conn = pool.getConnection();

        ProductDao productDao = new ProductDao(conn);
        Product product = productDao.getEntityById(id);
        conn.close();
        return product;
    }

    public User getUser(int id) throws SQLException {
        Connection conn = pool.getConnection();

        UserDao userDao = new UserDao(conn);
        OrderDao orderDao = new OrderDao(conn);

        User user = userDao.getEntityById(id);
        user.setOrder(orderDao.getEntityById(user.getOrder().getId()));
        conn.close();
        return user;
    }

    public List<Catalog> getCatalogs() throws SQLException, NamingException {
        Connection conn = pool.getConnection();

        CatalogDao catalogDao = new CatalogDao(conn);
        List<Catalog> catalogs = catalogDao.getAll();
        conn.close();
        return catalogs;
    }

    public Catalog getCatalog(int id) throws SQLException, NamingException {
        Connection conn = pool.getConnection();

        CatalogDao catalogDao = new CatalogDao(conn);
        Catalog catalog = catalogDao.getEntityById(id);
        conn.close();
        return catalog;
    }
}
