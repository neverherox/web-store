package servlet.user;

import model.entity.Catalog;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        IServiceFactory serviceFactory = new ServiceFactory();
        IUserService userService = serviceFactory.getUserService();
        List<Product> products = null;
        try {
            products = userService.getProducts();
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("user", user);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/jsp/user/user.jsp").forward(request, response);
    }
}
