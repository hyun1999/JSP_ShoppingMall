package shoppingMall.controller.cart;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.dto.CartItemDto;
import shoppingMall.service.CartService;

import java.io.IOException;
import java.util.List;

public class ViewCartCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");
        System.out.println(userId);

        if (userId == null) {
            response.sendRedirect("login.do");
            return;
        }

        List<CartItemDto> cartItems = cartService.getCartItems(userId);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/cart/cart.jsp").forward(request, response);
    }
}
