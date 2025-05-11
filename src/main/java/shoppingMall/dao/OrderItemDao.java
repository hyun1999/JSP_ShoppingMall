package shoppingMall.dao;

import shoppingMall.domain.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderItemDao {

    public void insertOrderItem(OrderItem item, Connection conn) throws SQLException {
        String sql = "INSERT INTO TB_ORDER_ITEM (" +
                "id_order_item, id_order, cn_order_item, no_product, no_user, " +
                "qt_unit_price, qt_order_item, qt_order_item_amount, " +
                "qt_order_item_delivery_fee, st_payment, no_register, da_first_date" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getIdOrderItem());
            pstmt.setString(2, item.getIdOrder());
            pstmt.setInt(3, item.getCnOrderItem());
            pstmt.setString(4, item.getNoProduct());
            pstmt.setString(5, item.getNoUser());
            pstmt.setInt(6, item.getQtUnitPrice());
            pstmt.setInt(7, item.getQtOrderItem());
            pstmt.setInt(8, item.getQtOrderItemAmount());
            pstmt.setInt(9, item.getQtOrderItemDeliveryFee());
            pstmt.setString(10, item.getStPayment());
            pstmt.setString(11, item.getNoRegister());
            pstmt.setTimestamp(12, Timestamp.valueOf(item.getDaFirstDate()));

            pstmt.executeUpdate();
        }
    }
}
