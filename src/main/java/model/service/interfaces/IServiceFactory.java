package model.service.interfaces;

public interface IServiceFactory {
    IUserService getUserService();

    IAdminService getAdminService();

    IService getService();
}
