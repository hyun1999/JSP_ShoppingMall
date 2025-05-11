package shoppingMall.controller.orderServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Order;
import shoppingMall.service.OrderService;

import java.io.IOException;

public class OrderSuccessCommand implements Command {
    private final OrderService orderService = new OrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        Order order = orderService.getOrderById(orderId);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/order/orderSuccess.jsp").forward(request, response);
    }
}

