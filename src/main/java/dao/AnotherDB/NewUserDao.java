package dao.AnotherDB;

import dao.interfaces.IUserDao;
import entity.User;

import java.util.ArrayList;

public class NewUserDao implements IUserDao {
    @Override
    public User GetUser(String login, String password) {
        return null;
    }

    @Override
    public void SetUser(User user) {

    }

    @Override
    public User GetUser(int id) {
        return null;
    }

    @Override
    public ArrayList<User> GetUsers() {
        return null;
    }

    @Override
    public void EditUser(User user) {

    }
}
