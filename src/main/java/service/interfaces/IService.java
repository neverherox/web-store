package service.interfaces;

import entity.Catalog;
import entity.Product;
import entity.User;

import java.util.ArrayList;

public interface IService {
    ArrayList<Product> GetProducts();
    User GetUser(String login, String password);
    void SetUser(User user);
    Product GetProduct(int id);
    User GetUser(int id);
    ArrayList<Catalog> GetCatalogs();
    Catalog GetCatalog(int id);
}
