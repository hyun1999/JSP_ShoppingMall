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
        List<Product> productList = productService.getAllProducts();
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/indexForm.jsp").forward(request, response);
    }
}
