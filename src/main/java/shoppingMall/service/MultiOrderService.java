package shoppingMall.service;

import shoppingMall.dao.CartDao;
import shoppingMall.dao.OrderDao;
import shoppingMall.dao.OrderItemDao;
import shoppingMall.dao.ProductDao;
import shoppingMall.domain.Order;
import shoppingMall.domain.OrderItem;
import shoppingMall.domain.Product;
import shoppingMall.dto.CartItemDto;
import shoppingMall.utils.JdbcDriver;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MultiOrderService {
    private final OrderDao orderDao = new OrderDao();
    private final OrderItemDao orderItemDao = new OrderItemDao();
    private final CartDao cartDao = new CartDao();
    private final ProductService productService = new ProductService();
    private final ProductDao productDao = new ProductDao();

    public String placeMultiItemOrder(String userId, String[] itemIds, String orderPerson,
                                      String receiver, String tel, String zip, String address, String place) {

        LocalDateTime now = LocalDateTime.now();
        String orderId = UUID.randomUUID().toString().replace("-", "").substring(0, 30);
        int totalAmount = 0;
        int totalDeliveryFee = 0;

        List<OrderItem> orderItems = new ArrayList<>();
        int orderItemSeq = 1;

        try (Connection conn = JdbcDriver.getConnection()) {
            conn.setAutoCommit(false);

            for (String itemId : itemIds) {
                CartItemDto cartItem = cartDao.selectCartItemById(itemId);
                if (cartItem == null) continue;

                Product product = productService.getProductById(cartItem.getProductId());
                int unitPrice = product.getQtSalePrice();
                int quantity = cartItem.getQuantity();
                int itemAmount = unitPrice * quantity;
                int deliveryFee = product.getQtDeliveryFee();

                // 재고 차감
                productDao.decreaseStock(product.getNoProduct(), quantity, conn);

                // 주문 품목 객체 생성
                OrderItem orderItem = new OrderItem();
                orderItem.setIdOrderItem(UUID.randomUUID().toString().replace("-", "").substring(0, 30));
                orderItem.setIdOrder(orderId);
                orderItem.setCnOrderItem(orderItemSeq++);
                orderItem.setNoProduct(product.getNoProduct());
                orderItem.setNoUser(userId);
                orderItem.setQtUnitPrice(unitPrice);
                orderItem.setQtOrderItem(quantity);
                orderItem.setQtOrderItemAmount(itemAmount);
                orderItem.setQtOrderItemDeliveryFee(deliveryFee);
                orderItem.setStPayment("20");
                orderItem.setNoRegister(userId);
                orderItem.setDaFirstDate(now);

                totalAmount += itemAmount;
                totalDeliveryFee += deliveryFee;

                orderItems.add(orderItem);
            }

            // 주문 객체 생성
            Order order = new Order();
            order.setIdOrder(orderId);
            order.setNoUser(userId);
            order.setQtOrderAmount(totalAmount);
            order.setQtDeliMoney(totalDeliveryFee);
            order.setQtDeliPeriod(3);
            order.setNmOrderPerson(orderPerson);
            order.setNmReceiver(receiver);
            order.setNoDeliveryZipno(zip);
            order.setNmDeliveryAddress(address);
            order.setNmReceiverTelno(tel);
            order.setNmDeliverySpace(place);
            order.setCdOrderType("10");
            order.setDaOrder(now);
            order.setStOrder("10");
            order.setStPayment("20");
            order.setNoRegister(userId);
            order.setDaFirstDate(now);

            // DB 저장
            orderDao.insertOrder(order, conn);
            for (OrderItem item : orderItems) {
                orderItemDao.insertOrderItem(item, conn);
            }

            // 장바구니에서 해당 항목 삭제
            for (String itemId : itemIds) {
                cartDao.deleteCartItem(itemId);
            }

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("다중 상품 주문 처리 실패", e);
        }

        return orderId;
    }
}
