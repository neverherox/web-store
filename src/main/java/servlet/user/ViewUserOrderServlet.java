package servlet.user;

import model.entity.User;
import model.service.implementation.UserServiceImpl;
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

        int userId = Integer.parseInt(request.getParameter("id"));
        User user = null;
        user = userService.getUser(userId);

        request.setAttribute("user", user);
        request.getRequestDispatcher("/jsp/user/user_order.jsp").forward(request, response);
    }

}
