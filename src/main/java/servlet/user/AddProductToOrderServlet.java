package servlet.user;

import model.entity.Order;
import model.entity.Product;
import model.entity.User;
import model.service.implementation.OrderServiceImpl;
import model.service.implementation.ProductServiceImpl;
import model.service.implementation.UserServiceImpl;
import model.service.interfaces.OrderService;
import model.service.interfaces.ProductService;
import model.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddProductToOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        ProductService productService = new ProductServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.getUser(userId);

        if (request.getParameter("addButton") != null) {

            int productId = Integer.parseInt(request.getParameter("add"));
            Product product = productService.getProduct(productId);

            Order order = user.getOrder();
            orderService.addProductToOrder(order, product);

        }
        response.sendRedirect("user");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
