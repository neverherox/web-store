package model.service.interfaces;

import model.entity.Order;
import model.entity.Product;
import model.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IAdminService extends IService {
    void DeleteProduct(Product product) throws SQLException;

    void AddProduct(Product product) throws SQLException;

    List<Order> GetOrders() throws SQLException;

    void EditProduct(Product product) throws SQLException;

    List<User> GetUsers() throws SQLException;
}
