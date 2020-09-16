package servlet.user;

import model.entity.Catalog;
import model.entity.Order;
import model.entity.Product;
import model.entity.User;
import model.service.interfaces.IServiceFactory;
import model.service.interfaces.IUserService;
import model.service.implementation.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewUserOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IUserService userService = serviceFactory.getUserService();
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = null;
        try {
            user = userService.getUser(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("/jsp/user/user_order.jsp").forward(request, response);
    }

}
