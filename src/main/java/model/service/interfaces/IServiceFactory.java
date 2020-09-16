package model.service.interfaces;

public interface IServiceFactory {
    IUserService GetUserService();

    IAdminService GetAdminService();

    IService GetService();
}
