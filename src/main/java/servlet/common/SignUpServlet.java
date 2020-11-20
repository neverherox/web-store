package servlet.common;

import model.entity.Order;
import model.entity.OrderStatus;
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

public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retype_password");
        if (!password.equals(retypePassword)) {
            request.setAttribute("exception", "Passwords don`t match");
            request.getRequestDispatcher("jsp/signup.jsp").forward(request, response);
            return;
        }
        Order order  = new Order();
        order.setStatus(OrderStatus.UNPAID);
        orderService.addOrder(order);
        User user = new User(login, password);
        userService.addUser(user);
        request.getRequestDispatcher("jsp/signin.jsp").forward(request, response);
    }
}
