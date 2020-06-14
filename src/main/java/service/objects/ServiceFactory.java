package service.objects;


import service.interfaces.IAdminService;
import service.interfaces.IService;
import service.interfaces.IServiceFactory;
import service.interfaces.IUserService;

public class ServiceFactory implements IServiceFactory {
    public IUserService GetUserService()
    {
        return new UserService();
    }
    public IAdminService GetAdminService()
    {
        return new AdminService();
    }
    public IService GetService()
    {
        return new Service();
    }
}
