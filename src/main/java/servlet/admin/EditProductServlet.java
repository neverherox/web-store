package servlet.admin;

import entity.Product;
import service.interfaces.IAdminService;
import service.interfaces.IServiceFactory;
import service.objects.AdminService;
import service.objects.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.GetAdminService();
        int id = Integer.parseInt(request.getParameter("productId"));
        Product product = adminService.GetProduct(id);
        product.setName(request.getParameter("name"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setDescription(request.getParameter("description"));
        adminService.EditProduct(product);
        response.sendRedirect("admin");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.GetAdminService();
        if(request.getParameter("editButton")!=null)
        {
            int id = Integer.parseInt(request.getParameter("edit"));
            Product product = adminService.GetProduct(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/jsp/admin/edit_product.jsp").forward(request, response);
        }
    }
}
