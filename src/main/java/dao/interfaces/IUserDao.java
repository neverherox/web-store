package dao.interfaces;

import entity.User;

import java.util.ArrayList;

public interface IUserDao {
    User GetUser(String login,String password);
    void SetUser(User user);
    User GetUser(int id);
    ArrayList<User> GetUsers();
    void EditUser(User user);
}
