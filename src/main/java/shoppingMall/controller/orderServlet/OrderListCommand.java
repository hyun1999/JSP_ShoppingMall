package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.controller.Command;
import shoppingMall.domain.Order;
import shoppingMall.service.OrderService;

import java.io.IOException;
import java.util.List;

public class OrderListCommand implements Command {
    private final OrderService orderService = new OrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.do");
            return;
        }

        List<Order> orderList = orderService.getOrdersByUserId(userId);
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/order/orderList.jsp").forward(request, response);
    }
}
