package servlet.admin;

import model.entity.Product;
import model.service.implementation.ProductServiceImpl;
import model.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();

        int id = Integer.parseInt(request.getParameter("productId"));
        Product product = null;
        product = productService.getProduct(id);

        product.setName(request.getParameter("name"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setDescription(request.getParameter("description"));

        productService.editProduct(product);

        response.sendRedirect("admin");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductService productService = new ProductServiceImpl();

        if (request.getParameter("editButton") != null) {
            int id = Integer.parseInt(request.getParameter("edit"));
            Product product = null;
            product = productService.getProduct(id);

            request.setAttribute("product", product);
            request.getRequestDispatcher("/jsp/admin/edit_product.jsp").forward(request, response);
        }
    }
}
