package shoppingMall.dao;

import shoppingMall.domain.CategoryProductMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryProductMappingDao {

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
}
