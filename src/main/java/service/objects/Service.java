package service.objects;

import dao.interfaces.ICatalogDao;
import dao.interfaces.IDaoFactory;
import dao.interfaces.IProductDao;
import dao.interfaces.IUserDao;
import dao.mySqlObjects.DaoFactory;
import entity.Catalog;
import entity.Product;
import entity.User;
import service.interfaces.IService;

import java.util.ArrayList;

public class Service implements IService {
    IDaoFactory daoFactory = new DaoFactory();

    public ArrayList<Product> GetProducts()
    {
        IProductDao productDao = daoFactory.GetProductDao();
        return productDao.GetProducts();
    }
    public User GetUser(String login, String password)
    {
        IUserDao userDao = daoFactory.GetUserDao();
        return userDao.GetUser(login,password);
    }
    public void SetUser(User user)
    {
        IUserDao userDao = daoFactory.GetUserDao();
        userDao.SetUser(user);
    }
    public Product GetProduct(int id)
    {
        IProductDao productDao = daoFactory.GetProductDao();
        return productDao.GetProduct(id);
    }
    public User GetUser(int id)
    {
        IUserDao userDao = daoFactory.GetUserDao();
        return userDao.GetUser(id);
    }
    public ArrayList<Catalog> GetCatalogs()
    {
        ICatalogDao catalogDao = daoFactory.GetCatalogDao();
        return catalogDao.GetCatalogs();
    }
    public Catalog GetCatalog(int id)
    {
        ICatalogDao catalogDao = daoFactory.GetCatalogDao();
        return catalogDao.GetCatalog(id);
    }
}
