package servlet.admin;

import entity.Product;
import service.interfaces.IAdminService;
import service.interfaces.IServiceFactory;
import service.objects.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.GetAdminService();
        if(request.getParameter("deleteButton")!=null)
        {
            int id = Integer.parseInt(request.getParameter("delete"));
            Product product = adminService.GetProduct(id);
            adminService.DeleteProduct(product);
        }
        response.sendRedirect("admin");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
