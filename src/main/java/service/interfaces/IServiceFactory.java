package service.interfaces;

import service.objects.AdminService;
import service.objects.Service;
import service.objects.UserService;

public interface IServiceFactory {
    IUserService GetUserService();
    IAdminService GetAdminService();
    IService GetService();
}
