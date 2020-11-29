package servlet.admin;

import model.entity.Product;
import model.service.implementation.ProductServiceImpl;
import model.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();

        if (request.getParameter("deleteButton") != null) {
            int id = Integer.parseInt(request.getParameter("delete"));
            Product product = productService.getProduct(id);
            productService.deleteProduct(product);
        }
        response.sendRedirect("admin");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
