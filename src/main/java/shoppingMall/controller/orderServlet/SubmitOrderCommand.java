package shoppingMall.controller.orderServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Product;
import shoppingMall.service.OrderService;
import shoppingMall.service.ProductService;

public class SubmitOrderCommand implements Command {
    private final OrderService orderService = new OrderService();
    private final ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId == null) {
            return "redirect:/login.do";
        }

        // 1. 클라이언트로부터 전달받은 값
        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        String orderPerson = request.getParameter("orderPerson");
        String receiver = request.getParameter("receiver");
        String receiverTel = request.getParameter("receiverTel");
        String zip = request.getParameter("zip");
        String address = request.getParameter("address");
        String deliveryPlace = request.getParameter("deliveryPlace");

        // 2. 상품 정보 조회
        Product product = productService.getProductById(productId);
        if (product == null) {
            request.setAttribute("error", "해당 상품을 찾을 수 없습니다.");
            return "error.jsp";
        }

        // 3. 가격 계산
        int unitPrice = product.getQtSalePrice();
        int deliveryFee = product.getQtDeliveryFee();
        int totalAmount = (unitPrice * quantity) + deliveryFee;

        // 4. 주문 처리
        String orderId = orderService.placeOrder(
                userId,
                productId,
                quantity,
                orderPerson,
                receiver,
                receiverTel,
                zip,
                address,
                deliveryPlace,
                deliveryFee,
                totalAmount
        );

        return "redirect:/orderSuccess.do?orderId=" + orderId;
    }
}
