package servlet.user;

import model.entity.Product;
import model.entity.User;
import model.service.implementation.ProductServiceImpl;
import model.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Product> products = null;
        products = productService.getProducts();

        request.setAttribute("user", user);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/jsp/user/user.jsp").forward(request, response);
    }
}
