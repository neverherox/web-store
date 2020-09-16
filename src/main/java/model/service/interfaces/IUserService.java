package model.service.interfaces;

import model.entity.Order;
import model.entity.Product;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService extends IService {
    void addOrder(Order order) throws SQLException;

    void deleteOrder(Order order) throws SQLException;

    void editOrder(Order order) throws SQLException;

    void editUser(User user) throws SQLException;

    Order getOrder(int orderId) throws SQLException;

    void deleteProductFromOrder(Order order, Product product) throws SQLException;

    void addProductToOrder(Order order, Product product) throws SQLException;
}
