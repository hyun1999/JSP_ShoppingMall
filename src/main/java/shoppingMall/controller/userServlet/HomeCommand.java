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
import java.util.ArrayList;
import java.util.List;

public class HomeCommand implements Command {

    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        String categoryIdParam = request.getParameter("categoryId");
        List<Product> productList;

        if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            try {
                int categoryId = Integer.parseInt(categoryIdParam);
                List<Integer> allCategoryIds = categoryService.getAllDescendantCategoryIds(categoryId);
                productList = productService.getProductsByCategoryIds(allCategoryIds);
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
