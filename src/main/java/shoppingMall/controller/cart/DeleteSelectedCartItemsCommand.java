package shoppingMall.controller.cart;

import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.service.CartService;

public class DeleteSelectedCartItemsCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String[] itemIds = request.getParameterValues("selectedItems");
        if (itemIds != null) {
            for (String itemId : itemIds) {
                cartService.deleteCartItem(itemId);
            }
        }
        return "redirect:/viewCart.do";
    }
}
