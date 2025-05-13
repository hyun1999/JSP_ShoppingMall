package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.dao.ProductDao;
import shoppingMall.service.CategoryService;

import java.io.IOException;

public class ProductCategoryMappingInsertCommand implements Command {
    private final CategoryService categoryService = new CategoryService();
    private final ProductDao productDao = new ProductDao();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("nbCategory"));

        if (categoryService.hasChildCategories(categoryId)) {
            request.setAttribute("error", "상품은 최하위 카테고리에만 매핑할 수 있습니다.");
            request.getRequestDispatcher("/admin/manageDisplayCategory.jsp").forward(request, response);
            return;
        }

        int productId = Integer.parseInt(request.getParameter("noProduct"));
        int order = Integer.parseInt(request.getParameter("cnOrder"));

        productDao.mapProductToCategory(productId, categoryId, order);
        response.sendRedirect("manageDisplayCategory.do?selectedCategoryId=" + categoryId);
    }
}
