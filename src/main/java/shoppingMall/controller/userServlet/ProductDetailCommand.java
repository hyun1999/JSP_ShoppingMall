package shoppingMall.controller.userServlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Category;
import shoppingMall.domain.Product;
import shoppingMall.service.CategoryService;
import shoppingMall.service.ProductService;
import java.util.List;
import java.io.IOException;

public class ProductDetailCommand implements Command {

    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");

        if (productId != null && !productId.isEmpty()) {
            Product product = productService.getProductById(productId);
            request.setAttribute("product", product);
        }
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        request.getRequestDispatcher("/indexForm.jsp").forward(request, response);
    }
}
