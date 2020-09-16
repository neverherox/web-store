package servlet.admin;

import model.entity.Order;
import model.entity.Product;
import model.entity.User;
import model.service.interfaces.IAdminService;
import model.service.interfaces.IServiceFactory;
import model.service.implementation.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewOrdersServlet")
public class ViewOrdersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.getAdminService();
        List<User> users = null;
        try {
            users = adminService.getUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("users", users);
        request.getRequestDispatcher("/jsp/admin/orders.jsp").forward(request, response);
    }
}
