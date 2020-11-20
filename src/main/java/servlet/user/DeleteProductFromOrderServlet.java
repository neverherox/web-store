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

public class DeleteProductFromOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        ProductService productService = new ProductServiceImpl();

        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = null;
        user = userService.getUser(userId);

        if (request.getParameter("deleteButton") != null) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = productService.getProduct(productId);
            Order order = user.getOrder();
            orderService.deleteProductFromOrder(order, product);

        }
        user = userService.getUser(userId);

        request.setAttribute("user", user);
        request.getRequestDispatcher("/jsp/user/user_order.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
