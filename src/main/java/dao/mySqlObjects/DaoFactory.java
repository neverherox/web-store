package dao.mySqlObjects;

import dao.interfaces.*;
public class DaoFactory implements IDaoFactory {

    public IUserDao GetUserDao()
    {
        return new UserDao();
    }

    public IProductDao GetProductDao()
    {
        return new ProductDao();
    }

    public IOrderDao GetOrderDao()
    {
        return new OrderDao();
    }

    public ICatalogDao GetCatalogDao() { return new CatalogDao(); }
}
