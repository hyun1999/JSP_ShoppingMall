package shoppingMall.controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.service.CartService;

import java.io.IOException;

public class DeleteCartItemCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        cartService.deleteCartItem(itemId);
        response.sendRedirect("viewCart.do");
    }
}
