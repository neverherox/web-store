package servlet.admin;

import model.entity.User;
import model.service.implementation.UserServiceImpl;
import model.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewOrdersServlet")
public class ViewOrdersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();

        List<User> users = null;
        users = userService.getUsers();

        request.setAttribute("users", users);
        request.getRequestDispatcher("/jsp/admin/orders.jsp").forward(request, response);
    }
}
