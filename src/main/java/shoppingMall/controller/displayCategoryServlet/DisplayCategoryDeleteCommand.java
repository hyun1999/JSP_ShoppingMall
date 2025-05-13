package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.service.CategoryService;
import shoppingMall.controller.Command;

import java.io.IOException;

public class DisplayCategoryDeleteCommand implements Command {
    private final CategoryService categoryService = new CategoryService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        boolean deleted = categoryService.deleteCategory(categoryId);

        if (!deleted) {
            request.setAttribute("error", "하위 카테고리가 존재하는 경우 삭제할 수 없습니다.");
        }

        request.getRequestDispatcher("manageDisplayCategory.do").forward(request, response);
    }
}
