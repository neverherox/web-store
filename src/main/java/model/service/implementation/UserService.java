package model.service.implementation;

import model.dao.implementation.CatalogDao;
import model.dao.pool.ConnectionPool;
import model.dao.implementation.OrderDao;
import model.dao.implementation.ProductDao;
import model.dao.implementation.UserDao;
import model.entity.Order;
import model.entity.Product;
import model.entity.User;
import model.service.interfaces.IUserService;

import java.sql.Connection;
import java.sql.SQLException;



public class UserService extends Service implements IUserService {
    private ConnectionPool pool = new ConnectionPool();


    public void deleteOrder(Order order) throws SQLException {
        Connection conn = pool.getConnection();

        OrderDao orderDao = new OrderDao(conn);
        orderDao.deleteEntity(order);
        conn.close();
    }

    public void editOrder(Order order) throws SQLException {
        Connection conn = pool.getConnection();

        OrderDao orderDao = new OrderDao(conn);
        orderDao.editEntity(order);
        conn.close();

    }

    public void addProductToOrder(Order order, Product product) throws SQLException {
        Connection conn = pool.getConnection();
        OrderDao orderDao = new OrderDao(conn);
        orderDao.addProductToOrder(order, product);
        conn.close();
    }

    public void editUser(User user) throws SQLException {
        Connection conn = pool.getConnection();

        UserDao userDao = new UserDao(conn);
        userDao.editEntity(user);
        conn.close();

    }

    public Order getOrder(int orderId) throws SQLException {
        Connection conn = pool.getConnection();

        OrderDao orderDao = new OrderDao(conn);

        Order order = orderDao.getEntityById(orderId);
        conn.close();
        return order;
    }

    public void deleteProductFromOrder(Order order, Product product) throws SQLException {
        Connection conn = pool.getConnection();
        OrderDao orderDao = new OrderDao(conn);
        orderDao.deleteProductFromOrder(order, product);
        conn.close();
    }
}
