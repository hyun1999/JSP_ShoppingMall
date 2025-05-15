package shoppingMall.controller.orderServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Product;
import shoppingMall.service.ProductService;

public class OrderFormCommand implements Command {
    private final ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Product product = productService.getProductById(productId);

        request.setAttribute("product", product);
        request.setAttribute("quantity", quantity);

        return "/order/orderForm.jsp";
    }
}
