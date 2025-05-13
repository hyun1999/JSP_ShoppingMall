package shoppingMall.controller.adminServlet.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Category;
import shoppingMall.domain.enums.YnFlag;
import shoppingMall.service.CategoryService;

import java.io.IOException;
import java.time.LocalDateTime;

public class DisplayCategoryInsertCommand implements Command {
    private final CategoryService categoryService = new CategoryService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int parentId = Integer.parseInt(request.getParameter("parentCategoryId"));
        int parentLevel = categoryService.getCategoryLevel(parentId);

        if (parentLevel >= 3) {
            request.setAttribute("error", "최대 3단계까지만 생성 가능합니다.");
            request.getRequestDispatcher("/admin/manageDisplayCategory.jsp").forward(request, response);
            return;
        }

        Category category = new Category();
        category.setParentCategoryId(parentId);
        category.setLevel(parentLevel + 1);
        category.setName(request.getParameter("name"));
        category.setFullCategoryName(request.getParameter("name"));
        category.setDescription(request.getParameter("description"));
        category.setOrder(Integer.parseInt(request.getParameter("order")));
        category.setUsed(YnFlag.fromDbValue(request.getParameter("used")));
        category.setDeleted(YnFlag.NO);
        category.setCreatedBy("admin");
        category.setCreatedAt(LocalDateTime.now());

        categoryService.createCategory(category);
        response.sendRedirect("manageDisplayCategory.do");
    }
}
