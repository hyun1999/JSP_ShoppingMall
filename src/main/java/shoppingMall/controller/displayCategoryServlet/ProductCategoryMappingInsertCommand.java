package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.dao.ProductDao;
import shoppingMall.service.CategoryService;

public class ProductCategoryMappingInsertCommand implements Command {
    private final CategoryService categoryService = new CategoryService();
    private final ProductDao productDao = new ProductDao();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long rawCategoryId = Long.parseLong(request.getParameter("nbCategory"));
        int categoryId = Math.abs((int)(rawCategoryId % Integer.MAX_VALUE));

        if (categoryService.hasChildCategories(categoryId)) {
            request.setAttribute("error", "상품은 최하위 카테고리에만 매핑할 수 있습니다.");
            return "/admin/manageDisplayCategory.jsp";
        }

        String productId = request.getParameter("noProduct");
        int order = Integer.parseInt(request.getParameter("cnOrder"));

        productDao.mapProductToCategory(productId, categoryId, order);
        return "redirect:/manageDisplayCategory.do?selectedCategoryId=" + categoryId;
    }
}
