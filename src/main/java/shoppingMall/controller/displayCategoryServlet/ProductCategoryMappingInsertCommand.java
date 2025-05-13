package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.service.CategoryProductMappingService;
import shoppingMall.utils.JdbcDriver;
import shoppingMall.controller.Command;

import java.io.IOException;
import java.sql.Connection;

public class ProductCategoryMappingInsertCommand implements Command {
    private final CategoryProductMappingService mappingService = new CategoryProductMappingService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("nbCategory"));
        String productId = request.getParameter("noProduct");

        try (Connection conn = JdbcDriver.getConnection()) {
            mappingService.createMapping(categoryId, productId, "admin", conn);
            response.sendRedirect(request.getContextPath() + "/mapProductToCategoryPage.do");

        } catch (Exception e) {
            throw new ServletException("상품 매핑 중 오류 발생", e);
        }
    }
}
