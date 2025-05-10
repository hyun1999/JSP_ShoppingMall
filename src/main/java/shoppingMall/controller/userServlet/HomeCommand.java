package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Category;
import shoppingMall.domain.Product;
import shoppingMall.service.CategoryService;
import shoppingMall.service.ProductService;

import java.io.IOException;
import java.util.List;

public class HomeCommand implements Command {

    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 카테고리 리스트는 항상 제공
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        String categoryIdParam = request.getParameter("categoryId");
        List<Product> productList;

        if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            try {
                int categoryId = Integer.parseInt(categoryIdParam);
                productList = productService.getProductsByCategory(categoryId);
            } catch (NumberFormatException e) {
                productList = productService.getAllProducts();
            }
        } else {
            productList = productService.getAllProducts();
        }

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/indexForm.jsp").forward(request, response);
    }
}
