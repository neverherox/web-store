package service.interfaces;

import entity.Order;
import entity.User;

import java.util.ArrayList;

public interface IUserService extends IService{
    void AddOrder(Order order);
    ArrayList<Order> GetOrders(int userId);
    void DeleteOrder(Order order);
    void EditOrder(Order order);
    void EditUser(User user);
    Order GetOrder(int orderId);

}
