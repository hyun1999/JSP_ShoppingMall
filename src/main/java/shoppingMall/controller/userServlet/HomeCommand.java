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
        List<Integer> categoryIds = null;

        try {
            boolean hasKeyword = query != null && !query.isBlank();

            if (categoryParam != null && !categoryParam.isBlank() && !"all".equals(categoryParam)) {
                try {
                    long rawCategoryId = Long.parseLong(categoryParam.trim());
                    int categoryId = Math.abs((int)(rawCategoryId % Integer.MAX_VALUE));

                    // ✅ 카테고리 ID 목록 가져오기
                    categoryIds = categoryService.getAllDescendantCategoryIds(categoryId);

                    // ✅ categoryIds가 null이거나 비어있다면 현재 ID라도 포함시키기
                    if (categoryIds == null || categoryIds.isEmpty()) {
                        categoryIds = List.of(categoryId);
                    }

                    System.out.println("Resolved categoryIds: " + categoryIds);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid categoryParam: " + categoryParam);
                }
            }

            // ✅ 상품 리스트 조건별 조회
            if (hasKeyword) {
                productList = productService.searchProductsByCategoryAndKeyword(categoryIds, query);
            } else if (categoryIds != null) {
                productList = productService.getProductsByCategoryIds(categoryIds);
            } else {
                productList = productService.getAllProducts();
            }

            // ✅ 정렬
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

        // ✅ 결과 전달
        request.setAttribute("productList", productList);
        request.setAttribute("query", query);
        request.setAttribute("selectedCategory", categoryParam);
        request.setAttribute("selectedSort", sort);

        System.out.println("Product list size: " + (productList != null ? productList.size() : 0));

        return "/indexForm.jsp";
    }
}
