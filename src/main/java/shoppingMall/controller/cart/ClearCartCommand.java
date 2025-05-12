package shoppingMall.controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.service.CartService;

import java.io.IOException;

public class ClearCartCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");

        cartService.clearCart(userId);
        response.sendRedirect("viewCart.do");
    }
}