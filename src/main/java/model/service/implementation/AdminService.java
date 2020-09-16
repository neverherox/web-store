package model.service.implementation;

import model.dao.implementation.CatalogDao;
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
import java.util.ArrayList;
import java.util.List;

public class AdminService extends Service implements IAdminService {


    public void deleteProduct(Product product) throws SQLException {
        Connection conn = ConnectionPool.getConnection();
        ProductDao productDao = new ProductDao(conn);
        productDao.deleteEntity(product);
        conn.close();
    }

    public void addProduct(Product product) throws SQLException {
        Connection conn = ConnectionPool.getConnection();
        ProductDao productDao = new ProductDao(conn);
        productDao.addEntity(product);
        conn.close();
    }

    public List<Order> getOrders() throws SQLException {
        Connection conn = ConnectionPool.getConnection();
        OrderDao orderDao = new OrderDao(conn);
        ProductDao productDao = new ProductDao(conn);
        List<Order> orders = orderDao.getAll();
        for (Order order : orders) {
            List<Product> products = new ArrayList<>();
            for (Product product : order.getProducts()) {
                product = productDao.getEntityById(product.getId());
                products.add(product);
            }
            order.setProducts(products);
        }
        conn.close();
        return orders;
    }

    public void editProduct(Product product) throws SQLException {
        Connection conn = ConnectionPool.getConnection();
        ProductDao productDao = new ProductDao(conn);
        productDao.editEntity(product);
        conn.close();
    }

    public List<User> getUsers() throws SQLException {
        Connection conn = ConnectionPool.getConnection();
        UserDao userDao = new UserDao(conn);
        OrderDao orderDao = new OrderDao(conn);
        ProductDao productDao = new ProductDao(conn);
        CatalogDao catalogDao = new CatalogDao(conn);

        List<User> users = userDao.getAll();
        for (User user : users) {
            user.setOrder(orderDao.getEntityById(user.getOrder().getId()));
            List<Product> products = new ArrayList<>();
            for (Product product : user.getOrder().getProducts()) {
                product = productDao.getEntityById(product.getId());
                product.setCatalog(catalogDao.getEntityById(product.getCatalog().getId()));
                products.add(product);
            }
            user.getOrder().setProducts(products);
        }
        conn.close();
        return users;
    }

}
