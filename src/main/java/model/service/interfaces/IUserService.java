package model.service.interfaces;

import model.entity.Order;
import model.entity.Product;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService extends IService {
    void AddOrder(Order order) throws SQLException;

    void DeleteOrder(Order order) throws SQLException;

    void EditOrder(Order order) throws SQLException;

    void EditUser(User user) throws SQLException;

    Order GetOrder(int orderId) throws SQLException;

    void DeleteProductFromOrder(Order order, Product product) throws SQLException;

    void AddProductToOrder(Order order, Product product) throws SQLException;
}
