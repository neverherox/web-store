package service.interfaces;

import entity.Catalog;
import entity.Order;
import entity.Product;
import entity.User;

import java.util.ArrayList;

public interface IAdminService extends IService{
    void DeleteProduct(Product product);
    void AddProduct(Product product);
    ArrayList<Order> GetOrders();
    void EditProduct(Product product);
    ArrayList<User>GetUsers();
}
