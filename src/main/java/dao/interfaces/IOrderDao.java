package dao.interfaces;

import entity.Order;

import java.util.ArrayList;

public interface IOrderDao {
    ArrayList<Order> GetOrders();
    void AddOrder(Order order);
    ArrayList<Order> GetOrders(int userId);
    void DeleteOrder(Order order);
    void EditOrder(Order order);
    Order GetOrder(int orderId);
}
