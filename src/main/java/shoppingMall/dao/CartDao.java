package shoppingMall.dao;

import shoppingMall.dto.CartItemDto;
import shoppingMall.utils.JdbcDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    public void insertCartItem(String userId, String productId, int quantity) {
        String sql = "INSERT INTO TB_BASKET_ITEM (nb_basket_item, nb_basket, no_product, no_user, qt_basket_item, qt_basket_item_price, qt_basket_item_amount, no_register, da_first_date, cn_basket_item_order) " +
                "VALUES (SEQ_TB_BASKET_ITEM.NEXTVAL, ?, ?, ?, ?, (SELECT qt_sale_price FROM tb_product WHERE no_product = ?), (SELECT qt_sale_price FROM tb_product WHERE no_product = ?) * ?, ?, SYSDATE, ?)";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int basketId = getOrCreateBasketId(conn, userId);

            stmt.setInt(1, basketId);
            stmt.setString(2, productId);
            stmt.setString(3, userId);
            stmt.setInt(4, quantity);
            stmt.setString(5, productId);
            stmt.setString(6, productId);
            stmt.setInt(7, quantity);
            stmt.setString(8, userId);
            stmt.setString(9, String.valueOf(0));

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CartItemDto> selectCartItemsByUser(String userId) {
        List<CartItemDto> list = new ArrayList<>();
        String sql = "SELECT * FROM TB_BASKET_ITEM WHERE no_user = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CartItemDto item = new CartItemDto();
                item.setItemId(rs.getInt("nb_basket_item"));
                item.setProductId(rs.getString("no_product"));
                item.setQuantity(rs.getInt("qt_basket_item"));
                item.setAmount(rs.getInt("qt_basket_item_amount"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateCartItem(String itemId, int quantity) {
        String sql = "UPDATE TB_BASKET_ITEM SET qt_basket_item = ?, qt_basket_item_amount = qt_basket_item_price * ? WHERE nb_basket_item = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, quantity);
            stmt.setString(3, itemId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCartItem(String itemId) {
        String sql = "DELETE FROM TB_BASKET_ITEM WHERE nb_basket_item = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, itemId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCartByUser(String userId) {
        String sql = "DELETE FROM TB_BASKET_ITEM WHERE no_user = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getOrCreateBasketId(Connection conn, String userId) throws SQLException {
        String selectSql = "SELECT nb_basket FROM TB_BASKET WHERE no_user = ?";
        try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("nb_basket");
            }
        }

        String insertSql = "INSERT INTO TB_BASKET (nb_basket, no_user, no_register, da_first_date) VALUES (SEQ_TB_BASKET.NEXTVAL, ?, ?, SYSDATE)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSql, new String[]{"nb_basket"})) {
            stmt.setString(1, userId);
            stmt.setString(2, userId);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        throw new SQLException("장바구니 생성 실패");
    }

    public CartItemDto selectCartItemById(String itemId) {
        String sql = "SELECT * FROM TB_BASKET_ITEM WHERE nb_basket_item = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CartItemDto item = new CartItemDto();
                item.setItemId(rs.getInt("nb_basket_item"));
                item.setProductId(rs.getString("no_product"));
                item.setQuantity(rs.getInt("qt_basket_item"));
                item.setAmount(rs.getInt("qt_basket_item_amount"));
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
