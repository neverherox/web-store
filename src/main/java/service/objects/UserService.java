package service.objects;

import dao.interfaces.IDaoFactory;
import dao.interfaces.IOrderDao;
import dao.interfaces.IUserDao;
import dao.mySqlObjects.DaoFactory;
import entity.Order;
import entity.User;
import service.interfaces.IUserService;

import java.util.ArrayList;


public class UserService extends Service implements IUserService {
    IDaoFactory daoFactory = new DaoFactory();

    public void AddOrder(Order order)
    {
        IOrderDao orderDao = daoFactory.GetOrderDao();
        orderDao.AddOrder(order);
    }
    public ArrayList<Order> GetOrders(int userId)
    {
        IOrderDao orderDao = daoFactory.GetOrderDao();
        return orderDao.GetOrders(userId);
    }
    public void DeleteOrder(Order order)
    {
        IOrderDao orderDao = daoFactory.GetOrderDao();
        orderDao.DeleteOrder(order);
    }
    public void EditOrder(Order order)
    {
        IOrderDao orderDao = daoFactory.GetOrderDao();
        orderDao.EditOrder(order);
    }
    public void EditUser(User user)
    {
        IUserDao userDao = daoFactory.GetUserDao();
        userDao.EditUser(user);
    }
    public Order GetOrder(int orderId)
    {
        IOrderDao orderDao = daoFactory.GetOrderDao();
        return orderDao.GetOrder(orderId);
    }

}
