package shoppingMall.controller.cart;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.service.CartService;

import java.io.IOException;

public class AddToCartCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.do");
            return;
        }

        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        cartService.addToCart(userId, productId, quantity);

        response.sendRedirect("viewCart.do");
    }
}