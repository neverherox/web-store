package dao.AnotherDB;

import dao.interfaces.*;

public class NewDaoFactory implements IDaoFactory {
    @Override
    public IUserDao GetUserDao() {
        return new NewUserDao();
    }

    @Override
    public IProductDao GetProductDao() {
        return null;
    }

    @Override
    public IOrderDao GetOrderDao() {
        return null;
    }

    @Override
    public ICatalogDao GetCatalogDao() {
        return null;
    }
}
