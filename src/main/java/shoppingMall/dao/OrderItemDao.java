package shoppingMall.dao;

import shoppingMall.domain.OrderItem;
import shoppingMall.utils.JdbcDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public List<OrderItem> getOrderItemsByOrderId(String orderId) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM TB_ORDER_ITEM WHERE id_order = ? ORDER BY cn_order_item";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setIdOrderItem(rs.getString("id_order_item"));
                item.setIdOrder(rs.getString("id_order"));
                item.setCnOrderItem(rs.getInt("cn_order_item"));
                item.setNoProduct(rs.getString("no_product"));
                item.setNoUser(rs.getString("no_user"));
                item.setQtUnitPrice(rs.getInt("qt_unit_price"));
                item.setQtOrderItem(rs.getInt("qt_order_item"));
                item.setQtOrderItemAmount(rs.getInt("qt_order_item_amount"));
                item.setQtOrderItemDeliveryFee(rs.getInt("qt_order_item_delivery_fee"));
                item.setStPayment(rs.getString("st_payment"));
                item.setNoRegister(rs.getString("no_register"));
                item.setDaFirstDate(rs.getTimestamp("da_first_date").toLocalDateTime());
                item.setConvertedDate(new Date(rs.getTimestamp("da_first_date").getTime()));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean cancelOrderIfBeforeShipping(String orderId) {
        String checkSql = "SELECT st_order FROM TB_ORDER WHERE id_order = ?";
        String updateSql = "UPDATE TB_ORDER SET st_order = '99' WHERE id_order = ?"; // '99' = 취소됨

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, orderId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                String status = rs.getString("st_order");
                if ("10".equals(status)) { // 배송 전 (주문완료 상태)
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setString(1, orderId);
                        int rows = updateStmt.executeUpdate();
                        return rows > 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
