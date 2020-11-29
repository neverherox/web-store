package servlet.user;


import model.entity.Order;
import model.entity.OrderStatus;
import model.entity.Product;
import model.entity.User;
import model.service.implementation.OrderServiceImpl;
import model.service.implementation.UserServiceImpl;
import model.service.interfaces.OrderService;
import model.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CheckOutOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.getUser(userId);

        List<Product> products = user.getOrder().getProducts();
        double orderPrice = orderService.countOrderPrice(user.getOrder());


        user.getOrder().setStatus(OrderStatus.PAID);
        orderService.editOrder(user.getOrder());

        Order order = new Order();
        order.setStatus(OrderStatus.UNPAID);
        orderService.addOrder(order);

        userService.editUser(user);

        request.setAttribute("user", user);
        request.setAttribute("products", products);
        request.setAttribute("order_price",orderPrice);
        request.setAttribute("firstName", request.getParameter("firstName"));
        request.setAttribute("lastName", request.getParameter("lastName"));
        request.setAttribute("address", request.getParameter("address"));
        request.setAttribute("country", request.getParameter("country"));
        request.setAttribute("city", request.getParameter("city"));

        request.getRequestDispatcher("/jsp/user/bill.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        int userId = Integer.parseInt(request.getParameter("userId"));

        User user = userService.getUser(userId);
        double orderPrice = orderService.countOrderPrice(user.getOrder());

        request.setAttribute("order_price", orderPrice);
        request.getRequestDispatcher("/jsp/user/checkout_order.jsp").forward(request, response);
    }
}
