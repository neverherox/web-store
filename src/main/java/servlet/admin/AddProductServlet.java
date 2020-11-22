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
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        Catalog catalog = null;
        catalog = catalogService.getCatalog(catalogId);


        Product product = new Product();
        product.setCatalog(catalog);
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);


        productService.addProduct(product);

        response.sendRedirect("admin");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/admin/add_product.jsp").forward(request, response);

    }
}
