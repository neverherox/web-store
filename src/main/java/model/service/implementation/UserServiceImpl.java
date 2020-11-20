package model.service.implementation;

import model.dao.implementation.OrderDao;
import model.dao.implementation.UserDao;
import model.entity.Order;
import model.entity.User;
import model.service.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    public List<User> getUsers(){
        UserDao userDao = new UserDao();
        List<User> users = userDao.getAll();
        return users;
    }
    public User getUser(String login, String password){

        UserDao userDao = new UserDao();
        User user = userDao.getUser(login, password);
        return user;
    }

    public void addUser(User user) {
        OrderDao orderDao = new OrderDao();
        List<Order> orders = orderDao.getAll();
        UserDao userDao = new UserDao();
        user.setOrder(orders.get(orders.size()-1));
        userDao.addEntity(user);
    }
    public User getUser(int id){
        UserDao userDao = new UserDao();
        User user = userDao.getEntityById(id);
        return user;
    }
    public void editUser(User user){

        UserDao userDao = new UserDao();
        userDao.editEntity(user);
    }
}
