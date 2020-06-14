package servlet.user;

import entity.Catalog;
import entity.Product;
import entity.User;
import service.interfaces.IServiceFactory;
import service.interfaces.IUserService;
import service.objects.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        IServiceFactory serviceFactory = new ServiceFactory();
        IUserService userService = serviceFactory.GetUserService();
        ArrayList<Product> products = userService.GetProducts();
        ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
        for(Product product:products)
        {
            Catalog catalog = userService.GetCatalog(product.getCatalogId());
            catalogs.add(catalog);
        }
        request.setAttribute("user", user);
        request.setAttribute("catalogs", catalogs);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/jsp/user/user.jsp").forward(request, response);
    }
}
