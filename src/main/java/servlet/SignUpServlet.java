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

public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retype_password");
        if (!password.equals(retypePassword))
        {
            request.setAttribute("exception", "Passwords don`t match");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        IServiceFactory serviceFactory = new ServiceFactory();
        IService service = serviceFactory.GetService();
        User user = new User(login,password, UserRole.user);
        service.SetUser(user);
        request.getRequestDispatcher("signin.jsp").forward(request, response);
    }
}
