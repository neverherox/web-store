package servlet.common;

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

public class SignInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        IServiceFactory serviceFactory = new ServiceFactory();
        IService service = serviceFactory.getService();
        User user = null;
        try {
            user = service.getUser(login, password);
        } catch (SQLException | NamingException throwables) {
            throwables.printStackTrace();
        }
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
