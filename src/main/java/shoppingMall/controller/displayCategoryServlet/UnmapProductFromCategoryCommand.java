package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.utils.JdbcDriver;
import shoppingMall.controller.Command;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UnmapProductFromCategoryCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String productId = request.getParameter("productId");

        String sql = "DELETE FROM tb_category_product_mapping WHERE nb_category = ? AND no_product = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setString(2, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("mappedProducts.do?categoryId=" + categoryId);
    }
}
