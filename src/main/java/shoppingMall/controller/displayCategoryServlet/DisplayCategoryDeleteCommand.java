package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.service.CategoryService;
import shoppingMall.controller.Command;

public class DisplayCategoryDeleteCommand implements Command {
    private final CategoryService categoryService = new CategoryService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        categoryService.deleteCategory(categoryId);
        response.sendRedirect("manageDisplayCategory.do");
    }
}
