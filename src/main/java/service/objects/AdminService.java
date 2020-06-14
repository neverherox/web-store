package service.objects;

import dao.interfaces.*;
import dao.mySqlObjects.DaoFactory;
import entity.Catalog;
import entity.Order;
import entity.Product;
import entity.User;
import service.interfaces.IAdminService;

import java.util.ArrayList;

public class AdminService extends Service implements  IAdminService{
    IDaoFactory daoFactory = new DaoFactory();


    public void DeleteProduct(Product product)
    {
        IProductDao productDao = daoFactory.GetProductDao();
        productDao.DeleteProduct(product);
    }
    public void AddProduct(Product product)
    {
        IProductDao productDao = daoFactory.GetProductDao();
        productDao.AddProduct(product);
    }
    public ArrayList<Order> GetOrders()
    {
        IOrderDao orderDao = daoFactory.GetOrderDao();
        return orderDao.GetOrders();
    }

    public void EditProduct(Product product)
    {
        IProductDao productDao = daoFactory.GetProductDao();
        productDao.EditProduct(product);
    }
    public ArrayList<User>GetUsers()
    {
        IUserDao userDao = daoFactory.GetUserDao();
        return userDao.GetUsers();
    }

}
