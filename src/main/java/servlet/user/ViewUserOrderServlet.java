package servlet.user;

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

public class ViewUserOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUser(userId);
        double orderPrice = orderService.countOrderPrice(user.getOrder());

        request.setAttribute("order_price", orderPrice);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/jsp/user/user_order.jsp").forward(request, response);
    }

}
