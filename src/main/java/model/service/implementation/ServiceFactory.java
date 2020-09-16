package model.service.implementation;


import model.service.interfaces.IAdminService;
import model.service.interfaces.IService;
import model.service.interfaces.IServiceFactory;
import model.service.interfaces.IUserService;

public class ServiceFactory implements IServiceFactory {
    public IUserService getUserService() {
        return new UserService();
    }

    public IAdminService getAdminService() {
        return new AdminService();
    }

    public IService getService() {
        return new Service();
    }
}
