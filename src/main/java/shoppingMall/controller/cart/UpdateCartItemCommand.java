package shoppingMall.controller.cart;

import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.service.CartService;

public class UpdateCartItemCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String itemId = request.getParameter("itemId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        cartService.updateCartItem(itemId, quantity);
        return "redirect:/viewCart.do";
    }
}
