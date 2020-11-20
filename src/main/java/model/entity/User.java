package model.entity;

import java.io.Serializable;

public class User extends Entity implements Serializable {
    private String login;
    private String password;
    private UserRole role;
    private Order order;

    public User() {

    }


    public User(int id, String login, String password, UserRole role, Order order) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.order = order;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.role = UserRole.USER;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
