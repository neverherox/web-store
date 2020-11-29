package servlet.admin;

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

public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add") != null) {
            request.getRequestDispatcher("/jsp/admin/add_product.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        List<Product> products = productService.getProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/jsp/admin/admin.jsp").forward(request, response);
    }
}
