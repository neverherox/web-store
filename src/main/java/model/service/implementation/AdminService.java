package model.service.implementation;

import model.dao.pool.ConnectionPool;
import model.dao.implementation.OrderDao;
import model.dao.implementation.ProductDao;
import model.dao.implementation.UserDao;
import model.entity.Order;
import model.entity.Product;
import model.entity.User;
import model.service.interfaces.IAdminService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminService extends Service implements IAdminService {

    private ConnectionPool pool = new ConnectionPool();

    public void deleteProduct(Product product) throws SQLException {
        Connection conn = pool.getConnection();
        ProductDao productDao = new ProductDao(conn);
        productDao.deleteEntity(product);
        conn.close();
    }

    public void addProduct(Product product) throws SQLException {
        Connection conn = pool.getConnection();
        ProductDao productDao = new ProductDao(conn);
        productDao.addEntity(product);
        conn.close();
    }

    public List<Order> getOrders() throws SQLException {
        Connection conn = pool.getConnection();
        OrderDao orderDao = new OrderDao(conn);
        List<Order> orders = orderDao.getAll();
        conn.close();
        return orders;
    }

    public void editProduct(Product product) throws SQLException {
        Connection conn = pool.getConnection();
        ProductDao productDao = new ProductDao(conn);
        productDao.editEntity(product);
        conn.close();
    }

    public List<User> getUsers() throws SQLException {
        Connection conn = pool.getConnection();
        UserDao userDao = new UserDao(conn);

        List<User> users = userDao.getAll();
        conn.close();
        return users;
    }

}
