package servlet.admin;

import model.entity.Product;
import model.service.interfaces.IAdminService;
import model.service.interfaces.IServiceFactory;
import model.service.implementation.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.getAdminService();
        if (request.getParameter("deleteButton") != null) {
            int id = Integer.parseInt(request.getParameter("delete"));
            Product product = null;
            try {
                product = adminService.getProduct(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                adminService.deleteProduct(product);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        response.sendRedirect("admin");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
