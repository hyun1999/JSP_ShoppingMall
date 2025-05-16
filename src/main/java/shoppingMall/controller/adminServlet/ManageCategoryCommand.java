package shoppingMall.controller.adminServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Category;
import shoppingMall.dto.CategoryDto;
import shoppingMall.service.CategoryService;

import java.util.List;

public class ManageCategoryCommand implements Command {
    private final CategoryService categoryService = new CategoryService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            List<Category> categoryList = categoryService.getAllCategories();
            request.setAttribute("categoryList", categoryList);
            return "/admin/categoryPage.jsp";
        }

        if ("create".equals(action)) {
            CategoryDto dto = new CategoryDto();
            dto.setName(request.getParameter("name"));
            dto.setDescription(request.getParameter("description"));
            dto.setParentCategoryId(parseInteger(request.getParameter("parentId")));
            categoryService.createCategory(dto.toEntity());

        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("categoryId"));
            Category category = categoryService.getCategoryById(id);

            category.setName(request.getParameter("name"));
            category.setDescription(request.getParameter("description"));
            Integer parentId = parseInteger(request.getParameter("parentId"));
            category.setParentCategoryId(parentId != null ? parentId : 0);

            categoryService.updateCategory(category);

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("categoryId"));
            categoryService.deleteCategory(id);
            return "redirect:/manageCategory.do";
        }

        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);
        return "/admin/categoryPage.jsp";
    }

    private Integer parseInteger(String value) {
        try {
            return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
