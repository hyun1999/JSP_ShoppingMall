package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.domain.Category;
import shoppingMall.domain.Product;
import shoppingMall.service.CategoryService;
import shoppingMall.dao.ProductDao;
import shoppingMall.controller.Command;

import java.io.IOException;
import java.util.List;

public class ProductCategoryMappingPageCommand implements Command {
    private final CategoryService categoryService = new CategoryService();
    private final ProductDao productDao = new ProductDao();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        // 선택된 카테고리 ID가 있을 경우에만 필터링
        String selectedCat = request.getParameter("selectedCategoryId");
        if (selectedCat != null && !selectedCat.isEmpty()) {
            int categoryId = Integer.parseInt(selectedCat);
            List<Product> filteredProducts = productDao.findUnmappedProducts(categoryId);
            request.setAttribute("productList", filteredProducts);
            request.setAttribute("selectedCategoryId", categoryId);
        }
        request.getRequestDispatcher("/admin/manageDisplayCategory.jsp").forward(request, response);
    }
}
