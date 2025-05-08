package shoppingMall.dao;

import shoppingMall.domain.Product;
import shoppingMall.utils.JdbcDriver;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    // 모든 상품 조회
    public List<Product> findAllProducts() {
        String sql = "SELECT * FROM tb_product";
        List<Product> products = new ArrayList<>();

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(mapProduct(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // 상품 등록
    public void insertProduct(Product product, Connection conn) throws SQLException {
        String sql = "INSERT INTO tb_product " +
                "(no_product, nm_product, nm_detail_explain, id_file, dt_start_date, dt_end_date, " +
                "qt_customer_price, qt_sale_price, qt_stock, qt_delivery_fee, no_register, da_first_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getNoProduct());
            ps.setString(2, product.getNmProduct());
            ps.setString(3, product.getNmDetailExplain());
            ps.setString(4, product.getIdFile());
            ps.setString(5, product.getDtStartDate());
            ps.setString(6, product.getDtEndDate());
            ps.setInt(7, product.getQtCustomerPrice());
            ps.setInt(8, product.getQtSalePrice());
            ps.setInt(9, product.getQtStock());
            ps.setInt(10, product.getQtDeliveryFee());
            ps.setString(11, product.getNoRegister());
            ps.setTimestamp(12, Timestamp.valueOf(product.getDaFirstDate()));

            ps.executeUpdate();
        }
    }


    // 상품 삭제
    public void deleteProduct(String noProduct) {
        String sql = "DELETE FROM tb_product WHERE no_product = ?";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, noProduct);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 상품 상세 조회
    public Product findProductById(String noProduct) {
        String sql = "SELECT * FROM tb_product WHERE no_product = ?";
        Product product = null;

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, noProduct);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = mapProduct(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    // 상품 수정
    public void updateProduct(Product product) {
        String sql = "UPDATE tb_product SET " +
                "nm_product = ?, nm_detail_explain = ?, id_file = ?, dt_start_date = ?, dt_end_date = ?, " +
                "qt_customer_price = ?, qt_sale_price = ?, qt_stock = ?, qt_delivery_fee = ?, no_register = ?, da_first_date = ? " +
                "WHERE no_product = ?";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getNmProduct());
            ps.setString(2, product.getNmDetailExplain());
            ps.setString(3, product.getIdFile());
            ps.setString(4, product.getDtStartDate());
            ps.setString(5, product.getDtEndDate());
            ps.setInt(6, product.getQtCustomerPrice());
            ps.setInt(7, product.getQtSalePrice());
            ps.setInt(8, product.getQtStock());
            ps.setInt(9, product.getQtDeliveryFee());
            ps.setString(10, product.getNoRegister());
            ps.setTimestamp(11, Timestamp.valueOf(product.getDaFirstDate()));
            ps.setString(12, product.getNoProduct());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 결과 ResultSet → Product 객체 매핑
    private Product mapProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setNoProduct(rs.getString("no_product"));
        product.setNmProduct(rs.getString("nm_product"));
        product.setNmDetailExplain(rs.getString("nm_detail_explain"));
        product.setIdFile(rs.getString("id_file"));
        product.setDtStartDate(rs.getString("dt_start_date"));
        product.setDtEndDate(rs.getString("dt_end_date"));
        product.setQtCustomerPrice(rs.getInt("qt_customer_price"));
        product.setQtSalePrice(rs.getInt("qt_sale_price"));
        product.setQtStock(rs.getInt("qt_stock"));
        product.setQtDeliveryFee(rs.getInt("qt_delivery_fee"));
        product.setNoRegister(rs.getString("no_register"));
        Timestamp ts = rs.getTimestamp("da_first_date");
        if (ts != null) {
            product.setDaFirstDate(ts.toLocalDateTime());
        }
        return product;
    }
}
