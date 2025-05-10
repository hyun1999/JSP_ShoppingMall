package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import shoppingMall.controller.Command;
import shoppingMall.domain.Product;
import shoppingMall.service.ProductService;

import java.io.IOException;
import java.util.List;

public class ProductByCategoryCommand implements Command {

    private final ProductService productService = new ProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryIdParam = request.getParameter("categoryId");

        if (categoryIdParam != null) {
            int categoryId = Integer.parseInt(categoryIdParam);
            List<Product> products = productService.getProductsByCategory(categoryId);
            request.setAttribute("productList", products);
            request.getRequestDispatcher("/indexForm.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "카테고리 ID가 없습니다.");
        }
    }
}
