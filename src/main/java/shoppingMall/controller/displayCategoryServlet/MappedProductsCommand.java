package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.domain.Category;
import shoppingMall.domain.Product;
import shoppingMall.service.CategoryService;
import shoppingMall.dao.ProductDao;
import shoppingMall.controller.Command;

import java.util.List;

public class MappedProductsCommand implements Command {
    private final CategoryService categoryService = new CategoryService();
    private final ProductDao productDao = new ProductDao();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        String catIdParam = request.getParameter("categoryId");
        if (catIdParam != null && !catIdParam.isEmpty()) {
            int categoryId = Integer.parseInt(catIdParam);
            List<Product> productList = productDao.findProductsByCategory(categoryId);
            request.setAttribute("productList", productList);
            request.setAttribute("selectedCategoryId", categoryId);
        }

        return "redirect:/mapProductToCategoryPage.do";
    }
}
