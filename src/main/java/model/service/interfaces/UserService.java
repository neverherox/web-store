package model.service.interfaces;

import model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUser(String login, String password);

    void addUser(User user);

    User getUser(int id);

    void editUser(User user);
}
