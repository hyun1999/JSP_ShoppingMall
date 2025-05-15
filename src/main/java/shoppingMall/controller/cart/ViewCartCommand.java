package shoppingMall.controller.cart;

import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.dto.CartItemDto;
import shoppingMall.service.CartService;

import java.util.List;

public class ViewCartCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "redirect:/login.do";
        }

        String userId = (String) session.getAttribute("userId");
        List<CartItemDto> cartItems = cartService.getCartItems(userId);
        request.setAttribute("cartItems", cartItems);

        return "/cart/cart.jsp";
    }
}
