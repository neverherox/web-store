package dao.interfaces;

import dao.mySqlObjects.CatalogDao;
import dao.mySqlObjects.OrderDao;
import dao.mySqlObjects.ProductDao;
import dao.mySqlObjects.UserDao;

import java.sql.Connection;

public interface IDaoFactory {
    IUserDao GetUserDao();
    IProductDao GetProductDao();
    IOrderDao GetOrderDao();
    ICatalogDao GetCatalogDao();
}
