package servlet.user;

import model.entity.Order;
import model.entity.Product;
import model.entity.User;
import model.service.interfaces.IServiceFactory;
import model.service.interfaces.IUserService;
import model.service.implementation.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteProductFromOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IUserService userService = serviceFactory.getUserService();
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = null;
        try {
            user = userService.getUser(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (request.getParameter("deleteButton") != null) {
            try {
                int productId = Integer.parseInt(request.getParameter("productId"));
                Product product = userService.getProduct(productId);
                Order order = user.getOrder();
                userService.deleteProductFromOrder(order, product);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            user = userService.getUser(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("/jsp/user/user_order.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
