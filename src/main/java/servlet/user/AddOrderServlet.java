package servlet.user;

import entity.Order;
import entity.OrderStatus;
import service.interfaces.IServiceFactory;
import service.interfaces.IUserService;
import service.objects.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IUserService userService = serviceFactory.GetUserService();
        int userId = Integer.parseInt(request.getParameter("userId"));
        if (request.getParameter("addButton") != null) {

            int productId = Integer.parseInt(request.getParameter("add"));
            Order order = new Order(userId, productId, OrderStatus.unpaid);
            userService.AddOrder(order);
        }
        response.sendRedirect("user");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
