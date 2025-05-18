package shoppingMall.service;

import shoppingMall.dao.OrderDao;
import shoppingMall.dao.OrderItemDao;
import shoppingMall.domain.Order;
import shoppingMall.domain.OrderItem;
import shoppingMall.domain.Product;
import shoppingMall.utils.JdbcDriver;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderService {

    private final OrderDao orderDao = new OrderDao();
    private final OrderItemDao orderItemDao = new OrderItemDao();
    private final ProductService productService = new ProductService();

    public String placeOrder(String userId, String productId, int quantity,
                             String orderPerson, String receiver, String tel,
                             String zip, String address, String place,
                             int deliveryFee, int totalAmount) {

        Product product = productService.getProductById(productId);
        String orderId = UUID.randomUUID().toString().replace("-", "").substring(0, 30);
        String orderItemId = UUID.randomUUID().toString().replace("-", "").substring(0, 30);
        LocalDateTime now = LocalDateTime.now();

        int unitPrice = product.getQtSalePrice();

        Order order = new Order();
        order.setIdOrder(orderId);
        order.setNoUser(userId);
        order.setQtOrderAmount(totalAmount);
        order.setQtDeliMoney(deliveryFee);
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

        OrderItem item = new OrderItem();
        item.setIdOrderItem(orderItemId);
        item.setIdOrder(orderId);
        item.setCnOrderItem(1);
        item.setNoProduct(productId);
        item.setNoUser(userId);
        item.setQtUnitPrice(unitPrice);
        item.setQtOrderItem(quantity);
        item.setQtOrderItemAmount(unitPrice * quantity);
        item.setQtOrderItemDeliveryFee(deliveryFee);
        item.setStPayment("20");
        item.setNoRegister(userId);
        item.setDaFirstDate(now);

        try (Connection conn = JdbcDriver.getConnection()) {
            conn.setAutoCommit(false);

            orderDao.insertOrder(order, conn);
            orderItemDao.insertOrderItem(item, conn);

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("주문 처리 실패", e);
        }

        return orderId;
    }


    public Order getOrderById(String orderId) {
        return orderDao.getOrderById(orderId);
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        return orderItemDao.getOrderItemsByOrderId(orderId);
    }
}
