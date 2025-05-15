package shoppingMall.controller.orderServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.service.MultiOrderService;

public class SubmitSelectedOrderCommand implements Command {
    private final MultiOrderService service = new MultiOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId == null) {
            return "redirect:/login.do";
        }

        String[] itemIds = request.getParameterValues("itemIds");
        String orderPerson = request.getParameter("orderPerson");
        String receiver = request.getParameter("receiver");
        String receiverTel = request.getParameter("receiverTel");
        String zip = request.getParameter("zip");
        String address = request.getParameter("address");
        String deliveryPlace = request.getParameter("deliveryPlace");

        String orderId = service.placeMultiItemOrder(userId, itemIds, orderPerson, receiver, receiverTel, zip, address, deliveryPlace);

        return "redirect:/orderSuccess.do?orderId=" + orderId;
    }
}
