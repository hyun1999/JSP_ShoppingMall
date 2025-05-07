package shoppingMall.controller.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.domain.Product;
import shoppingMall.service.CategoryService;
import shoppingMall.service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manageProduct")
public class ManageProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = productService.getAllProducts();
        request.setAttribute("productList", productService.getAllProducts());
        request.setAttribute("categoryList", categoryService.getAllCategories());

        request.getRequestDispatcher("/admin/productPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            // 상품 생성
        } else if ("update".equals(action)) {
            // 상품 수정
        } else if ("delete".equals(action)) {
            // 상품 삭제
        }

        response.sendRedirect(request.getContextPath() + "/admin/manageCategory");
    }
}
