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
import java.util.Comparator;
import java.util.List;

public class HomeCommand implements Command {

    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 전체 카테고리 항상 제공
        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        // 2. 파라미터 수집
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
                } catch (NumberFormatException e) {
                    // category 파라미터가 잘못된 숫자인 경우 무시하고 전체 조회
                    categoryIds = null;
                }
            }

            if (hasKeyword) {
                productList = productService.searchProductsByCategoryAndKeyword(categoryIds, query);
            } else if (categoryIds != null) {
                productList = productService.getProductsByCategoryIds(categoryIds);
            } else {
                productList = productService.getAllProducts();
            }

            // 정렬 처리
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

        // 3. 결과 전달
        request.setAttribute("productList", productList);
        request.setAttribute("query", query); // 검색창에 유지
        request.setAttribute("selectedCategory", categoryParam); // 드롭다운 유지
        request.setAttribute("selectedSort", sort); // 정렬 유지
        request.getRequestDispatcher("/indexForm.jsp").forward(request, response);
    }
}
