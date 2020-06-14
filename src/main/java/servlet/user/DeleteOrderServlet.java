package servlet.user;

import entity.Order;
import entity.Product;
import entity.User;
import service.interfaces.IServiceFactory;
import service.interfaces.IUserService;
import service.objects.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class DeleteOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IUserService userService = serviceFactory.GetUserService();
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.GetUser(userId);
        if (request.getParameter("deleteButton") != null) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order userOrder = userService.GetOrder(orderId);
            userService.DeleteOrder(userOrder);
        }
        ArrayList<Order> orders = userService.GetOrders(userId);
        ArrayList<Product> products = new ArrayList<Product>();
        for(Order order:orders)
        {
            Product product = userService.GetProduct(order.getProductId());
            products.add(product);
        }
        request.setAttribute("orders", orders);
        request.setAttribute("user", user);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/jsp/user/user_orders.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
