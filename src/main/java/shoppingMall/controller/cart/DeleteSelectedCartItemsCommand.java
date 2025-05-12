// DeleteSelectedCartItemsCommand.java
package shoppingMall.controller.cart;

import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.service.CartService;

import java.io.IOException;

public class DeleteSelectedCartItemsCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] itemIds = request.getParameterValues("selectedItems");
        if (itemIds != null) {
            for (String itemId : itemIds) {
                cartService.deleteCartItem(itemId);
            }
        }
        response.sendRedirect("viewCart.do");
    }
}
