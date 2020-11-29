package servlet.admin;

import model.entity.Order;
import model.entity.Product;
import model.service.implementation.OrderServiceImpl;
import model.service.implementation.ProductServiceImpl;
import model.service.interfaces.OrderService;
import model.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderServiceImpl();

        if (request.getParameter("deleteButton") != null) {
            int id = Integer.parseInt(request.getParameter("delete"));
            Order order  = orderService.getOrder(id);
            orderService.deleteOrder(order);
        }
        response.sendRedirect("orders");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
