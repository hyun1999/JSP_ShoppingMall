package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.domain.Category;
import shoppingMall.domain.Product;
import shoppingMall.service.CategoryService;
import shoppingMall.dao.ProductDao;
import shoppingMall.controller.Command;

import java.util.List;

public class ProductCategoryMappingPageCommand implements Command {
    private final CategoryService categoryService = new CategoryService();
    private final ProductDao productDao = new ProductDao();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        String selectedCat = request.getParameter("selectedCategoryId");
        if (selectedCat != null && !selectedCat.isEmpty()) {
            int categoryId = Integer.parseInt(selectedCat);
            List<Product> filteredProducts = productDao.findUnmappedProducts(categoryId);
            request.setAttribute("productList", filteredProducts);
            request.setAttribute("selectedCategoryId", categoryId);
        }

        return "/admin/manageDisplayCategory.jsp";
    }
}
