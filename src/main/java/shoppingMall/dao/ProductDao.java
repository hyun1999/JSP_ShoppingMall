package shoppingMall.dao;

import shoppingMall.domain.Product;

import java.util.List;

public class ProductDao {
    // 모든 카테고리 목록 조회 (삭제되지 않은 카테고리)
    public List<Product> findAllProducts() {
//        String sql = "SELECT * FROM tb_category WHERE yn_delete = 'N' ORDER BY cn_order";
//        List<Category> categories = new ArrayList<>();
//
//        try (Connection conn = JdbcDriver.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                categories.add(mapCategory(rs));  // 각 카테고리 매핑
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return categories;
        return null;
    }

}
