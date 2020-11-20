package model.service.interfaces;

import model.entity.Order;
import model.entity.Product;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    void addOrder(Order order);

    void deleteOrder(Order order);

    void editOrder(Order order);

    Order getOrder(int orderId);

    void deleteProductFromOrder(Order order, Product product);

    void addProductToOrder(Order order, Product product);
}
