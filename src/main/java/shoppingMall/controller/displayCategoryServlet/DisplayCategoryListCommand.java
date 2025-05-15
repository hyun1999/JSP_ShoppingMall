package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.dao.ProductDao;
import shoppingMall.domain.Category;
import shoppingMall.domain.Product;
import shoppingMall.service.CategoryService;
import shoppingMall.controller.Command;

import java.util.List;

public class DisplayCategoryListCommand implements Command {
    private final CategoryService categoryService = new CategoryService();
    private final ProductDao productDao = new ProductDao();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        List<Product> allProducts = productDao.findAllProducts();
        request.setAttribute("allProducts", allProducts);

        String selectedCatId = request.getParameter("selectedCategoryId");
        if (selectedCatId != null && !selectedCatId.isEmpty()) {
            int categoryId = Integer.parseInt(selectedCatId);
            List<Product> mapped = productDao.findProductsByCategory(categoryId);
            request.setAttribute("mappedProducts", mapped);
            request.setAttribute("selectedCategoryId", categoryId);
        }

        return "/admin/manageDisplayCategory.jsp";
    }
}
