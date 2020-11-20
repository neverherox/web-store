package servlet.common;

import model.entity.User;
import model.entity.UserRole;
import model.service.implementation.UserServiceImpl;
import model.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();

        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;
        user = userService.getUser(login, password);

        if (user.getRole().equals(UserRole.ADMIN)) {
            request.getSession(true).setAttribute("user", user);
            response.sendRedirect("admin");
        } else {
            request.getSession(true).setAttribute("user", user);
            response.sendRedirect("user");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/signin.jsp").forward(request, response);
    }
}
