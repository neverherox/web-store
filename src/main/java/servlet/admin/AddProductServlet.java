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

public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int catalogId = Integer.parseInt(request.getParameter("catalogId"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.GetAdminService();
        Product product = new Product(catalogId,name,price,description);
        adminService.AddProduct(product);
        response.sendRedirect("admin");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
