package shoppingMall.dao;

import shoppingMall.domain.CategoryProductMapping;
import shoppingMall.utils.JdbcDriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryProductMappingDao {

    // 매핑 등록
    public void insert(CategoryProductMapping mapping, Connection conn) throws SQLException {
        String sql = "INSERT INTO tb_category_product_mapping " +
                "(nb_category, no_product, cn_order, no_register, da_first_date) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mapping.getNbCategory());
            stmt.setString(2, mapping.getNoProduct());
            stmt.setInt(3, mapping.getCnOrder());
            stmt.setString(4, mapping.getNoRegister());
            stmt.setTimestamp(5, new java.sql.Timestamp(mapping.getDaFirstDate().getTime()));
            stmt.executeUpdate();
        }
    }

    // 매핑 삭제
    public void delete(int categoryId, String productId) throws SQLException {
        String sql = "DELETE FROM tb_category_product_mapping WHERE nb_category = ? AND no_product = ?";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            stmt.setString(2, productId);
            stmt.executeUpdate();
        }
    }
}
