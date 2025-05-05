package shoppingMall.controller.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.domain.Category;
import shoppingMall.domain.enums.YnFlag;
import shoppingMall.service.CategoryService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/admin/manageCategory")
public class ManageCategoryServlet extends HttpServlet {
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/admin/categoryPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // 카테고리 추가
            Category category = new Category();
            category.setName(request.getParameter("name"));
            category.setDescription(request.getParameter("description"));

            String parentIdStr = request.getParameter("parentId");
            if (parentIdStr != null && !parentIdStr.isEmpty()) {
                System.out.println("parentId :"+parentIdStr);
                category.setParentCategoryId(Integer.parseInt(parentIdStr));
            } else {
                category.setParentCategoryId(0);  // 부모 카테고리가 없는 경우
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
        }

        response.sendRedirect(request.getContextPath() + "/admin/manageCategory");
    }
}
