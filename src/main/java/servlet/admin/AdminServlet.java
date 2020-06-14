package servlet.admin;

import entity.Catalog;
import entity.Product;
import entity.User;
import service.interfaces.IAdminService;
import service.interfaces.IServiceFactory;
import service.objects.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("add")!=null)
        {
            request.getRequestDispatcher("/WEB-INF/jsp/admin/add_product.jsp").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.GetAdminService();
        ArrayList<Product> products = adminService.GetProducts();
        ArrayList<Catalog> allCatalogs = adminService.GetCatalogs();
        ArrayList<Catalog> catalogs = new ArrayList<Catalog>();
        for(Product product:products)
        {
         Catalog catalog = adminService.GetCatalog(product.getCatalogId());
         catalogs.add(catalog);
        }
        request.setAttribute("catalogs", catalogs);
        request.setAttribute("allCatalogs", allCatalogs);
        request.setAttribute("products", products);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/admin.jsp").forward(request, response);
    }
}
