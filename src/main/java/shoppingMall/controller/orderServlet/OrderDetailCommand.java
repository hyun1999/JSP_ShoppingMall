package shoppingMall.controller.orderServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Order;
import shoppingMall.domain.OrderItem;
import shoppingMall.service.OrderService;

import java.io.IOException;
import java.util.List;

public class OrderDetailCommand implements Command {
    private final OrderService orderService = new OrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        if (orderId == null || orderId.isEmpty()) {
            response.sendRedirect("login.do");
            return;
        }

        Order order = orderService.getOrderById(orderId);
        List<OrderItem> items = orderService.getOrderItemsByOrderId(orderId);

        request.setAttribute("order", order);
        request.setAttribute("items", items);
        request.getRequestDispatcher("/order/orderDetail.jsp").forward(request, response);
    }
}
