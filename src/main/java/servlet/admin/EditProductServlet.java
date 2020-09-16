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

public class EditProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.GetAdminService();
        int id = Integer.parseInt(request.getParameter("productId"));
        Product product = null;
        try {
            product = adminService.GetProduct(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        product.setName(request.getParameter("name"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setDescription(request.getParameter("description"));
        try {
            adminService.EditProduct(product);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("admin");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.GetAdminService();
        if (request.getParameter("editButton") != null) {
            int id = Integer.parseInt(request.getParameter("edit"));
            Product product = null;
            try {
                product = adminService.GetProduct(id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("product", product);
            request.getRequestDispatcher("/jsp/admin/edit_product.jsp").forward(request, response);
        }
    }
}
