package servlet.user;

import entity.Order;
import entity.OrderStatus;
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

public class CheckoutOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IUserService userService = serviceFactory.GetUserService();
        User user = null;
        Product product = null;
        Order order = null;
        if (request.getParameter("checkoutButton") != null) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            user = userService.GetUser(userId);
            int productId = Integer.parseInt(request.getParameter("checkout"));
            product = userService.GetProduct(productId);
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            order = userService.GetOrder(orderId);
        }
        request.setAttribute("user", user);
        request.setAttribute("product", product);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/WEB-INF/jsp/user/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IUserService userService = serviceFactory.GetUserService();
        int userId = Integer.parseInt(request.getParameter("userId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order userOrder = userService.GetOrder(orderId);
        Product product = userService.GetProduct(productId);
        User user = userService.GetUser(userId);
        String cardNumber = request.getParameter("card_number");
        String cvv = request.getParameter("cvv");
        if (cardNumber.length() != 16 || cvv.length() != 3)
        {
            request.setAttribute("user", user);
            request.setAttribute("product", product);
            request.setAttribute("order", userOrder);
            request.setAttribute("exception", "Incorrect input");
            request.getRequestDispatcher("/WEB-INF/jsp/user/checkout.jsp").forward(request, response);
            return;
        }
        double userMoney = user.getMoney();
        double productPrice = product.getPrice();
        if (userMoney >= productPrice && userOrder.getStatus().equals(OrderStatus.unpaid)) {
            userOrder.setStatus(OrderStatus.paid);
            user.setMoney(userMoney - productPrice);
            userService.EditOrder(userOrder);
            userService.EditUser(user);
        }
        ArrayList<Order> orders = userService.GetOrders(userId);
        ArrayList<Product> products = new ArrayList<Product>();
        for (Order order : orders) {
            product = userService.GetProduct(order.getProductId());
            products.add(product);
        }
        request.setAttribute("user", user);
        request.setAttribute("products", products);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/jsp/user/user_orders.jsp").forward(request, response);
    }
}
