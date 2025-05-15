package shoppingMall.controller.orderServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.controller.Command;
import shoppingMall.domain.Order;
import shoppingMall.service.OrderService;

import java.util.List;

public class OrderListCommand implements Command {
    private final OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "redirect:/login.do";
        }

        String userId = (String) session.getAttribute("userId");
        List<Order> orderList = orderService.getOrdersByUserId(userId);
        request.setAttribute("orderList", orderList);

        return "/order/orderList.jsp";
    }
}
