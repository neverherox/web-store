package model.service.interfaces;

import model.entity.Order;
import model.entity.Product;
import model.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IAdminService extends IService {
    void deleteProduct(Product product) throws SQLException;

    void addProduct(Product product) throws SQLException;

    List<Order> getOrders() throws SQLException;

    void editProduct(Product product) throws SQLException;

    List<User> getUsers() throws SQLException;
}
