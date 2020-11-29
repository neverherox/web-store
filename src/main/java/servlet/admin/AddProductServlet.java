package servlet.admin;

import model.entity.Catalog;
import model.entity.Product;
import model.service.implementation.CatalogServiceImpl;
import model.service.implementation.ProductServiceImpl;
import model.service.interfaces.CatalogService;
import model.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CatalogService catalogService = new CatalogServiceImpl();
        ProductService productService = new ProductServiceImpl();

        int catalogId = Integer.parseInt(request.getParameter("catalogId"));
        Catalog catalog = catalogService.getCatalog(catalogId);

        Product product = new Product();
        product.setImage(request.getParameter("image"));
        product.setCatalog(catalog);
        product.setName(request.getParameter("name"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setDescription(request.getParameter("description"));

        productService.addProduct(product);

        response.sendRedirect("admin");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/admin/add_product.jsp").forward(request, response);

    }
}
