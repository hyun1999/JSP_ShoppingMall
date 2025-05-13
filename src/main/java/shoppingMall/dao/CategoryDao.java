package shoppingMall.dao;

import shoppingMall.domain.Category;
import shoppingMall.domain.enums.YnFlag;
import shoppingMall.utils.JdbcDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public List<Category> findChildren(int parentId) {
        String sql = "SELECT * FROM tb_category WHERE nb_parent_category = ? AND yn_delete = 'N'";
        List<Category> children = new ArrayList<>();

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, parentId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    children.add(mapCategory(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return children;
    }
    // 모든 카테고리 목록 조회 (삭제되지 않은 카테고리)
    public List<Category> findAll() {
        String sql = "SELECT * FROM tb_category WHERE yn_delete = 'N' ORDER BY cn_order";
        List<Category> categories = new ArrayList<>();

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                categories.add(mapCategory(rs));  // 각 카테고리 매핑
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    // 카테고리 삽입
    public boolean insert(Category category) {
        String sql = "INSERT INTO tb_category " +
                "(nb_category, nb_parent_category, nm_category, nm_full_category, nm_explain, " +
                " cn_level, cn_order, yn_use, yn_delete, no_register, da_first_date) " +
                "VALUES (category_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, category.getParentCategoryId(), Types.INTEGER);
            ps.setString(2, category.getName());
            ps.setString(3, category.getFullCategoryName());
            ps.setString(4, category.getDescription());
            ps.setObject(5, category.getLevel(), Types.INTEGER);
            ps.setInt(6, category.getOrder());
            ps.setString(7, category.getUsed().getDbValue());
            ps.setString(8, category.getDeleted().getDbValue());
            ps.setString(9, category.getCreatedBy());
            ps.setTimestamp(10, Timestamp.valueOf(category.getCreatedAt()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 카테고리 정보 업데이트
    public boolean update(Category category) {
        String sql = "UPDATE tb_category SET " +
                "nm_category = ?, nm_full_category = ?, nm_explain = ?, " +
                "cn_level = ?, cn_order = ?, yn_use = ?, nb_parent_category = ? " +
                "WHERE nb_category = ?";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, category.getName());
            ps.setString(2, category.getFullCategoryName());
            ps.setString(3, category.getDescription());
            ps.setObject(4, category.getLevel(), Types.INTEGER);
            ps.setInt(5, category.getOrder());
            ps.setString(6, category.getUsed().getDbValue());
            ps.setInt(7,category.getParentCategoryId());
            ps.setInt(8, category.getCategoryId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 카테고리 삭제 (실제로 삭제하지 않고 yn_delete를 'Y'로 변경)
    public boolean delete(int categoryId) {
        String sql = "UPDATE tb_category SET yn_delete = 'Y' WHERE nb_category = ?";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 카테고리 ID로 조회
    public Category findById(int categoryId) {
        String sql = "SELECT * FROM tb_category WHERE nb_category = ?";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCategory(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // 카테고리가 없으면 null 반환
    }

    // ResultSet을 Category 객체로 매핑하는 공통 메서드
    private Category mapCategory(ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setCategoryId(rs.getInt("nb_category"));
        c.setParentCategoryId(rs.getObject("nb_parent_category") != null ? rs.getInt("nb_parent_category") : 0);
        c.setName(rs.getString("nm_category"));
        c.setFullCategoryName(rs.getString("nm_full_category"));
        c.setDescription(rs.getString("nm_explain"));
        c.setLevel(rs.getObject("cn_level") != null ? rs.getInt("cn_level") : null);
        c.setOrder(rs.getInt("cn_order"));
        c.setUsed(YnFlag.fromDbValue(rs.getString("yn_use")));
        c.setDeleted(YnFlag.fromDbValue(rs.getString("yn_delete")));
        c.setCreatedBy(rs.getString("no_register"));
        Timestamp timestamp = rs.getTimestamp("da_first_date");
        if (timestamp != null) {
            c.setCreatedAt(timestamp.toLocalDateTime());
        }
        return c;
    }
}
