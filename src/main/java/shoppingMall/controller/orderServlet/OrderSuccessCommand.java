package shoppingMall.controller.orderServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Order;
import shoppingMall.service.OrderService;

public class OrderSuccessCommand implements Command {
    private final OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");
        Order order = orderService.getOrderById(orderId);
        request.setAttribute("order", order);

        return "/order/orderSuccess.jsp";
    }
}
