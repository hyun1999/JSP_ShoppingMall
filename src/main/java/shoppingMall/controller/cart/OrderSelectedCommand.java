package shoppingMall.controller.cart;

import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.dto.CartItemDto;
import shoppingMall.service.CartService;

import java.util.ArrayList;
import java.util.List;

public class OrderSelectedCommand implements Command {
    private final CartService cartService = new CartService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            return "redirect:/login.do";
        }

        String userId = (String) session.getAttribute("userId");
        String[] selectedItemIds = request.getParameterValues("selectedItems");

        if (selectedItemIds == null || selectedItemIds.length == 0) {
            request.setAttribute("error", "선택된 상품이 없습니다.");
            return "/cart/cart.jsp";
        }

        List<CartItemDto> selectedItems = new ArrayList<>();
        for (String itemId : selectedItemIds) {
            CartItemDto item = cartService.getCartItemById(itemId);
            if (item != null) {
                selectedItems.add(item);
            }
        }

        request.setAttribute("selectedItems", selectedItems);
        return "/order/orderSelectedForm.jsp";
    }
}
