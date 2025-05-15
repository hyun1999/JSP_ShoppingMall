package shoppingMall.controller.cart;

import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.service.CartService;

public class ClearCartCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "redirect:/login.do";
        }

        String userId = (String) session.getAttribute("userId");
        cartService.clearCart(userId);

        return "redirect:/viewCart.do";
    }
}
