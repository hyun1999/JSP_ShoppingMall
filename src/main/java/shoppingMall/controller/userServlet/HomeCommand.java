package shoppingMall.controller.userServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Category;
import shoppingMall.domain.Product;
import shoppingMall.service.CategoryService;
import shoppingMall.service.ProductService;

import java.util.Comparator;
import java.util.List;

public class HomeCommand implements Command {

    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        String categoryParam = request.getParameter("category");
        String query = request.getParameter("query");
        String sort = request.getParameter("sort");

        List<Product> productList;

        try {
            boolean hasKeyword = query != null && !query.isBlank();
            List<Integer> categoryIds = null;

            if (categoryParam != null && !categoryParam.isBlank() && !"all".equals(categoryParam)) {
                try {
                    int categoryId = Integer.parseInt(categoryParam.trim());
                    categoryIds = categoryService.getAllDescendantCategoryIds(categoryId);
                } catch (NumberFormatException ignored) {}
            }

            if (hasKeyword) {
                productList = productService.searchProductsByCategoryAndKeyword(categoryIds, query);
            } else if (categoryIds != null) {
                productList = productService.getProductsByCategoryIds(categoryIds);
            } else {
                productList = productService.getAllProducts();
            }

            if (sort != null) {
                switch (sort) {
                    case "price_asc":
                        productList.sort(Comparator.comparingInt(Product::getQtSalePrice));
                        break;
                    case "price_desc":
                        productList.sort(Comparator.comparingInt(Product::getQtSalePrice).reversed());
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            productList = productService.getAllProducts();
        }

        request.setAttribute("productList", productList);
        request.setAttribute("query", query);
        request.setAttribute("selectedCategory", categoryParam);
        request.setAttribute("selectedSort", sort);

        return "/indexForm.jsp";
    }
}
