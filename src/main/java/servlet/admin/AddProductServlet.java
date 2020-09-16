package servlet.admin;

import model.entity.Catalog;
import model.entity.Product;
import model.service.implementation.AdminService;
import model.service.interfaces.IAdminService;
import model.service.interfaces.IServiceFactory;
import model.service.implementation.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int catalogId = Integer.parseInt(request.getParameter("catalogId"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.getAdminService();
        Catalog catalog = null;
        try {
            catalog = adminService.getCatalog(catalogId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        Product product = new Product();
        product.setCatalog(catalog);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);

        try {
            adminService.addProduct(product);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("admin");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
