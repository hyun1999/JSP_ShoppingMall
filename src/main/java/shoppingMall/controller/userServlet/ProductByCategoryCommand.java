package shoppingMall.controller.userServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Product;
import shoppingMall.service.ProductService;

import java.util.List;

public class ProductByCategoryCommand implements Command {

    private final ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String categoryIdParam = request.getParameter("categoryId");

        if (categoryIdParam != null) {
            try {
                long rawId = Long.parseLong(categoryIdParam);
                int categoryId = Math.abs((int)(rawId % Integer.MAX_VALUE));

                List<Product> products = productService.getProductsByCategory(categoryId);
                request.setAttribute("productList", products);
                return "/indexForm.jsp";

            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                request.setAttribute("error", "잘못된 categoryId 형식입니다.");
                return "/errorPage.jsp";
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("error", "categoryId가 없습니다.");
            return "/errorPage.jsp";
        }
    }
}
