package shoppingMall.controller.orderServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.dao.OrderItemDao;

public class CancelOrderCommand implements Command {
    private final OrderItemDao orderItemDao = new OrderItemDao();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");

        boolean result = orderItemDao.cancelOrderIfBeforeShipping(orderId);

        if (result) {
            request.setAttribute("message", "주문이 성공적으로 취소되었습니다.");
        } else {
            request.setAttribute("message", "배송이 시작된 주문은 취소할 수 없습니다.");
        }

        return "/order/orderCancelResult.jsp";
    }
}
