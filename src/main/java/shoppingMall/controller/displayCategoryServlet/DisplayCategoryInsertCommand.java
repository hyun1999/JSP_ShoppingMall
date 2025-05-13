package shoppingMall.controller.adminServlet.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.domain.Category;
import shoppingMall.domain.enums.YnFlag;
import shoppingMall.service.CategoryService;
import shoppingMall.controller.Command;

import java.io.IOException;
import java.time.LocalDateTime;

public class DisplayCategoryInsertCommand implements Command {
    private final CategoryService categoryService = new CategoryService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        category.setParentCategoryId(0); // 최상위로 설정
        category.setName(request.getParameter("name"));
        category.setFullCategoryName(request.getParameter("name")); // 기본 설정
        category.setDescription(request.getParameter("description"));
        category.setLevel(Integer.parseInt(request.getParameter("level")));
        category.setOrder(Integer.parseInt(request.getParameter("order")));
        category.setUsed(YnFlag.fromDbValue(request.getParameter("used")));
        category.setDeleted(YnFlag.NO);
        category.setCreatedBy("admin");
        category.setCreatedAt(LocalDateTime.now());

        categoryService.createCategory(category);
        response.sendRedirect("manageDisplayCategory.do");
    }
}
