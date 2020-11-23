package servlet.user;

import model.dao.implementation.OrderDao;
import model.dao.implementation.UserDao;
import model.entity.Order;
import model.entity.OrderStatus;
import model.entity.Product;
import model.entity.User;
import model.service.implementation.OrderServiceImpl;
import model.service.implementation.UserServiceImpl;
import model.service.interfaces.OrderService;
import model.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckOutOrderServlet")
public class CheckOutOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        int userId = Integer.parseInt(request.getParameter("userId"));

        User user = userService.getUser(userId);
        double orderPrice = orderService.countOrderPrice(user.getOrder());

        request.setAttribute("order_price", orderPrice);
        request.getRequestDispatcher("/jsp/user/checkout_order.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.getUser(userId);

        user.getOrder().setStatus(OrderStatus.PAID);
        orderService.editOrder(user.getOrder());

        Order order = new Order();
        order.setStatus(OrderStatus.UNPAID);
        orderService.addOrder(order);

        userService.editUser(user);

        response.sendRedirect("user");
    }
}
