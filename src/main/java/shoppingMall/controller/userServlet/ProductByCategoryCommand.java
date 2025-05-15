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
            int categoryId = Integer.parseInt(categoryIdParam);
            List<Product> products = productService.getProductsByCategory(categoryId);
            request.setAttribute("productList", products);
            return "/indexForm.jsp";
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
