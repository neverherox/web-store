package model.service.implementation;

import model.dao.implementation.OrderDao;
import model.entity.Order;
import model.entity.Product;
import model.service.interfaces.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    public List<Order> getOrders() {
        OrderDao orderDao = new OrderDao();
        List<Order> orders = orderDao.getAll();
        return orders;
    }

    public void addOrder(Order order) {
        OrderDao orderDao = new OrderDao();
        orderDao.addEntity(order);
    }

    public void deleteOrder(Order order) {

        OrderDao orderDao = new OrderDao();
        orderDao.deleteEntity(order);
    }

    public void editOrder(Order order) {

        OrderDao orderDao = new OrderDao();
        orderDao.editEntity(order);

    }

    public void addProductToOrder(Order order, Product product) {
        OrderDao orderDao = new OrderDao();
        orderDao.addProductToOrder(order, product);
    }

    public Order getOrder(int orderId) {

        OrderDao orderDao = new OrderDao();

        Order order = orderDao.getEntityById(orderId);
        return order;
    }

    public void deleteProductFromOrder(Order order, Product product) {
        OrderDao orderDao = new OrderDao();
        orderDao.deleteProductFromOrder(order, product);
    }

    public double countOrderPrice(Order order) {
        int sum = 0;
        for (Product product : order.getProducts()) {
            sum += product.getPrice();
        }
        return sum;
    }

    @Override
    public List<Order> getEmptyOrders() {
        OrderDao orderDao = new OrderDao();
        return orderDao.getEmptyOrders();
    }
}
