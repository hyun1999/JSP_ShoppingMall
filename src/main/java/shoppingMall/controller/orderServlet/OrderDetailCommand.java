package shoppingMall.controller.orderServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Order;
import shoppingMall.domain.OrderItem;
import shoppingMall.service.OrderService;

import java.util.List;

public class OrderDetailCommand implements Command {
    private final OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");
        if (orderId == null || orderId.isEmpty()) {
            return "redirect:/login.do";
        }

        Order order = orderService.getOrderById(orderId);
        List<OrderItem> items = orderService.getOrderItemsByOrderId(orderId);

        request.setAttribute("order", order);
        request.setAttribute("items", items);

        return "/order/orderDetail.jsp";
    }
}
