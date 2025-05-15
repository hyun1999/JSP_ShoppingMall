package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.service.CategoryService;
import shoppingMall.controller.Command;

public class DisplayCategoryDeleteCommand implements Command {
    private final CategoryService categoryService = new CategoryService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        boolean deleted = categoryService.deleteCategory(categoryId);

        if (!deleted) {
            request.setAttribute("error", "하위 카테고리가 존재하는 경우 삭제할 수 없습니다.");
        }

        return "/manageDisplayCategory.do";
    }
}
