package servlet.common;

import model.entity.Order;
import model.entity.OrderStatus;
import model.entity.User;
import model.entity.UserRole;
import model.service.interfaces.IService;
import model.service.interfaces.IServiceFactory;
import model.service.implementation.ServiceFactory;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retype_password");
        if (!password.equals(retypePassword)) {
            request.setAttribute("exception", "Passwords don`t match");
            request.getRequestDispatcher("jsp/signup.jsp").forward(request, response);
            return;
        }
        IServiceFactory serviceFactory = new ServiceFactory();
        IService service = serviceFactory.GetService();
        User user = new User(login, password, UserRole.USER);
        Order order = new Order();
        order.setStatus(OrderStatus.UNPAID);
        try {
            service.AddOrder(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            service.SetUser(user);
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("jsp/signin.jsp").forward(request, response);
    }
}
