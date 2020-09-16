package servlet.admin;

import model.entity.Catalog;
import model.entity.Product;
import model.entity.User;
import model.service.interfaces.IAdminService;
import model.service.interfaces.IServiceFactory;
import model.service.implementation.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            request.getRequestDispatcher("/jsp/admin/add_product.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.getAdminService();
        List<Product> products = null;
        try {
            products = adminService.getProducts();
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("products", products);
        request.getRequestDispatcher("/jsp/admin/admin.jsp").forward(request, response);
    }
}
