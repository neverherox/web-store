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
import java.util.ArrayList;
import java.util.List;


public class UserService extends Service implements IUserService {


    public void DeleteOrder(Order order) throws SQLException {
        Connection conn = ConnectionPool.getConnection();

        OrderDao orderDao = new OrderDao(conn);
        orderDao.deleteEntity(order);
        conn.close();
    }

    public void EditOrder(Order order) throws SQLException {
        Connection conn = ConnectionPool.getConnection();

        OrderDao orderDao = new OrderDao(conn);
        orderDao.editEntity(order);
        conn.close();

    }

    public void AddProductToOrder(Order order, Product product) throws SQLException {
        Connection conn = ConnectionPool.getConnection();
        OrderDao orderDao = new OrderDao(conn);
        orderDao.addProductToOrder(order, product);
        conn.close();
    }

    public void EditUser(User user) throws SQLException {
        Connection conn = ConnectionPool.getConnection();

        UserDao userDao = new UserDao(conn);
        userDao.editEntity(user);
        conn.close();

    }

    public Order GetOrder(int orderId) throws SQLException {
        Connection conn = ConnectionPool.getConnection();

        OrderDao orderDao = new OrderDao(conn);
        ProductDao productDao = new ProductDao(conn);
        CatalogDao catalogDao = new CatalogDao(conn);

        Order order = orderDao.getEntityById(orderId);
        List<Product> products = new ArrayList<>();
        for (Product product : order.getProducts()) {
            product = productDao.getEntityById(product.getId());
            product.setCatalog(catalogDao.getEntityById(product.getCatalog().getId()));
            products.add(product);
        }
        order.setProducts(products);
        conn.close();
        return order;
    }

    public void DeleteProductFromOrder(Order order, Product product) throws SQLException {
        Connection conn = ConnectionPool.getConnection();
        OrderDao orderDao = new OrderDao(conn);
        orderDao.deleteProductFromOrder(order, product);
        conn.close();
    }
}
