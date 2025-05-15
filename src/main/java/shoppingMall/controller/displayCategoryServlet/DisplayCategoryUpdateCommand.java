package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.domain.Category;
import shoppingMall.domain.enums.YnFlag;
import shoppingMall.service.CategoryService;
import shoppingMall.controller.Command;

public class DisplayCategoryUpdateCommand implements Command {
    private final CategoryService categoryService = new CategoryService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Category category = new Category();
        category.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
        category.setName(request.getParameter("name"));
        category.setFullCategoryName(request.getParameter("name"));
        category.setDescription(request.getParameter("description"));
        category.setOrder(Integer.parseInt(request.getParameter("order")));
        category.setUsed(YnFlag.fromDbValue(request.getParameter("used")));

        categoryService.updateCategory(category);
        return "redirect:/manageDisplayCategory.do";
    }
}
