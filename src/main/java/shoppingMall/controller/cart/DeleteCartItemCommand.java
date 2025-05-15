package shoppingMall.controller.cart;

import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.service.CartService;

public class DeleteCartItemCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String itemId = request.getParameter("itemId");
        cartService.deleteCartItem(itemId);
        return "redirect:/viewCart.do";
    }
}
