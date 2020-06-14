package servlet.admin;

import entity.Order;
import entity.Product;
import entity.User;
import service.interfaces.IAdminService;
import service.interfaces.IServiceFactory;
import service.objects.AdminService;
import service.objects.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ViewOrdersServlet")
public class ViewOrdersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IServiceFactory serviceFactory = new ServiceFactory();
        IAdminService adminService = serviceFactory.GetAdminService();
        ArrayList<Order>orders = adminService.GetOrders();
        ArrayList<Product>products=adminService.GetProducts();
        ArrayList<User>users = adminService.GetUsers();
        request.setAttribute("orders",orders);
        request.setAttribute("products",products);
        request.setAttribute("users",users);
        request.getRequestDispatcher("/WEB-INF/jsp/admin/orders.jsp").forward(request, response);
    }
}
