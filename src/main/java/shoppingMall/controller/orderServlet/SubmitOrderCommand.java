package shoppingMall.controller.orderServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.service.OrderService;

public class SubmitOrderCommand implements Command {
    private final OrderService orderService = new OrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId == null) {
            return "redirect:/login.do";
        }

        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        String orderPerson = request.getParameter("orderPerson");
        String receiver = request.getParameter("receiver");
        String receiverTel = request.getParameter("receiverTel");
        String zip = request.getParameter("zip");
        String address = request.getParameter("address");
        String deliveryPlace = request.getParameter("deliveryPlace");

        String orderId = orderService.placeOrder(userId, productId, quantity, orderPerson, receiver, receiverTel, zip, address, deliveryPlace);

        return "redirect:/orderSuccess.do?orderId=" + orderId;
    }
}
