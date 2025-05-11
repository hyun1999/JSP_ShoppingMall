package shoppingMall.dao;

import shoppingMall.domain.Order;
import shoppingMall.utils.JdbcDriver;

import java.sql.*;

public class OrderDao {

    public void insertOrder(Order order, Connection conn) throws SQLException {
        String sql = "INSERT INTO TB_ORDER (" +
                "id_order, no_user, qt_order_amount, qt_deli_money, qt_deli_period, " +
                "nm_order_person, nm_receiver, no_delivery_zipno, nm_delivery_address, " +
                "nm_receiver_telno, nm_delivery_space, cd_order_type, da_order, st_order, " +
                "st_payment, no_register, da_first_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, order.getIdOrder());
            pstmt.setString(2, order.getNoUser());
            pstmt.setInt(3, order.getQtOrderAmount());
            pstmt.setInt(4, order.getQtDeliMoney());
            pstmt.setInt(5, order.getQtDeliPeriod());
            pstmt.setString(6, order.getNmOrderPerson());
            pstmt.setString(7, order.getNmReceiver());
            pstmt.setString(8, order.getNoDeliveryZipno());
            pstmt.setString(9, order.getNmDeliveryAddress());
            pstmt.setString(10, order.getNmReceiverTelno());
            pstmt.setString(11, order.getNmDeliverySpace());
            pstmt.setString(12, order.getCdOrderType());
            pstmt.setTimestamp(13, Timestamp.valueOf(order.getDaOrder()));
            pstmt.setString(14, order.getStOrder());
            pstmt.setString(15, order.getStPayment());
            pstmt.setString(16, order.getNoRegister());
            pstmt.setTimestamp(17, Timestamp.valueOf(order.getDaFirstDate()));

            pstmt.executeUpdate();
        }
    }

    public Order getOrderById(String orderId) {
        String sql = "SELECT * FROM TB_ORDER WHERE id_order = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, orderId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order();
                    order.setIdOrder(rs.getString("id_order"));
                    order.setNoUser(rs.getString("no_user"));
                    order.setQtOrderAmount(rs.getInt("qt_order_amount"));
                    order.setQtDeliMoney(rs.getInt("qt_deli_money"));
                    order.setQtDeliPeriod(rs.getInt("qt_deli_period"));
                    order.setNmOrderPerson(rs.getString("nm_order_person"));
                    order.setNmReceiver(rs.getString("nm_receiver"));
                    order.setNoDeliveryZipno(rs.getString("no_delivery_zipno"));
                    order.setNmDeliveryAddress(rs.getString("nm_delivery_address"));
                    order.setNmReceiverTelno(rs.getString("nm_receiver_telno"));
                    order.setNmDeliverySpace(rs.getString("nm_delivery_space"));
                    order.setCdOrderType(rs.getString("cd_order_type"));
                    order.setDaOrder(rs.getTimestamp("da_order").toLocalDateTime());
                    order.setStOrder(rs.getString("st_order"));
                    order.setStPayment(rs.getString("st_payment"));
                    order.setNoRegister(rs.getString("no_register"));
                    order.setDaFirstDate(rs.getTimestamp("da_first_date").toLocalDateTime());
                    return order;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("주문 조회 중 오류 발생", e);
        }
        return null;
    }
}
