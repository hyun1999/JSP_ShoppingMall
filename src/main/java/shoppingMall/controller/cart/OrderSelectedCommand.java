package shoppingMall.controller.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.controller.Command;
import shoppingMall.dto.CartItemDto;
import shoppingMall.service.CartService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderSelectedCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.do");
            return;
        }

        String[] selectedItemIds = request.getParameterValues("selectedItems");
        if (selectedItemIds == null || selectedItemIds.length == 0) {
            request.setAttribute("error", "선택된 상품이 없습니다.");
            request.getRequestDispatcher("/cart/cart.jsp").forward(request, response);
            return;
        }

        List<CartItemDto> selectedItems = new ArrayList<>();
        for (String itemId : selectedItemIds) {
            CartItemDto item = cartService.getCartItemById(itemId); // 이 메서드를 CartService에 구현해야 함
            if (item != null) {
                selectedItems.add(item);
            }
        }

        request.setAttribute("selectedItems", selectedItems);
        request.getRequestDispatcher("/order/orderSelectedForm.jsp").forward(request, response);
    }
}
