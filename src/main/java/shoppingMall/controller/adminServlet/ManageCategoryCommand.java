package shoppingMall.controller.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Category;
import shoppingMall.domain.enums.YnFlag;
import shoppingMall.service.CategoryService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ManageCategoryCommand implements Command {
    private final CategoryService categoryService = new CategoryService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(request.getMethod().equals("GET")){
            List<Category> categoryList = categoryService.getAllCategories();
            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("/admin/categoryPage.jsp").forward(request, response);
            return;
        }
        if ("create".equals(action)) {
            System.out.println(request.getParameter("name"));
            // 카테고리 추가
            Category category = new Category();
            category.setName(request.getParameter("name"));
            category.setDescription(request.getParameter("description"));

            String parentIdStr = request.getParameter("parentId");
            if (parentIdStr != null && !parentIdStr.isEmpty()) {
                category.setParentCategoryId(Integer.parseInt(parentIdStr));
            } else {
                category.setParentCategoryId(0);
            }

            category.setUsed(YnFlag.YES);
            category.setDeleted(YnFlag.NO);
            category.setCreatedAt(LocalDateTime.now());
            category.setCreatedBy("admin");

            categoryService.createCategory(category);

        } else if ("update".equals(action)) {

            // 카테고리 수정
            int id = Integer.parseInt(request.getParameter("categoryId"));

            Category category = categoryService.getCategoryById(id);
            category.setName(request.getParameter("name"));
            category.setDescription(request.getParameter("description"));

            String parentIdStr = request.getParameter("parentId");
            System.out.println("부모 ID: "+parentIdStr);
            if (parentIdStr != null && !parentIdStr.isEmpty()) {
                category.setParentCategoryId(Integer.parseInt(parentIdStr));
            } else {
                category.setParentCategoryId(0);
            }

            categoryService.updateCategory(category);

        } else if ("delete".equals(action)) {
            // 카테고리 삭제
            int id = Integer.parseInt(request.getParameter("categoryId"));
            categoryService.deleteCategory(id);
            response.sendRedirect("manageCategory.do");
            return;
        }

        List<Category> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);

        request.getRequestDispatcher("/admin/categoryPage.jsp").forward(request, response);
    }
}
