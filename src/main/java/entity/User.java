package entity;

import java.io.Serializable;

public class User implements Serializable{
    private int id;
    private String login;
    private String password;
    private UserRole role;
    private double money;

    public User()
    {

    }
    public User(int id,String login, String password, UserRole role,double money)
    {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.money = money;
    }
    public User(String login, String password, UserRole role)
    {
        this.login = login;
        this.password = password;
        this.role = role;
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

    public double getMoney(){return money;}
    public void setMoney(double money){this.money = money;}
}
