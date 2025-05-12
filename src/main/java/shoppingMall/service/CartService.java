package shoppingMall.service;

import shoppingMall.dao.CartDao;
import shoppingMall.dto.CartItemDto;

import java.util.List;

public class CartService {
    private final CartDao cartDao = new CartDao();

    public void addToCart(String userId, String productId, int quantity) {
        cartDao.insertCartItem(userId, productId, quantity);
    }

    public List<CartItemDto> getCartItems(String userId) {
        return cartDao.selectCartItemsByUser(userId);
    }

    public void updateCartItem(String itemId, int quantity) {
        cartDao.updateCartItem(itemId, quantity);
    }

    public void deleteCartItem(String itemId) {
        cartDao.deleteCartItem(itemId);
    }

    public void clearCart(String userId) {
        cartDao.clearCartByUser(userId);
    }

    public CartItemDto getCartItemById(String itemId) {
        return cartDao.selectCartItemById(itemId);
    }
}
