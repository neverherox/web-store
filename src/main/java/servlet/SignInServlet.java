package servlet;

import entity.User;
import entity.UserRole;
import service.interfaces.IService;
import service.interfaces.IServiceFactory;
import service.objects.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        IServiceFactory serviceFactory = new ServiceFactory();
        IService service = serviceFactory.GetService();
        User user = service.GetUser(login, password);
        if (user.getRole().equals(UserRole.admin))
        {
            request.getSession(true).setAttribute("user", user);
            response.sendRedirect("admin");
        }
        else
        {
            request.getSession(true).setAttribute("user", user);
            response.sendRedirect("user");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signin.jsp").forward(request, response);
    }
}
