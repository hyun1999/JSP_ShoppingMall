package shoppingMall.service;

import shoppingMall.dao.CartDao;
import shoppingMall.dao.ProductDao;
import shoppingMall.domain.Product;
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
        CartItemDto item = cartDao.selectCartItemById(itemId);

        if (item != null) {
            ProductDao productDao = new ProductDao();
            Product product = productDao.findProductById(item.getProductId());

            if (product != null) {
                item.setDeliveryFee(product.getQtDeliveryFee());
            }
        }

        return item;
    }

}
