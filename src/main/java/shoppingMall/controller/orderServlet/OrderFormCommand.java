package shoppingMall.controller.orderServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Product;
import shoppingMall.service.ProductService;

import java.io.IOException;

public class OrderFormCommand implements Command {
    private final ProductService productService = new ProductService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Product product = productService.getProductById(productId);

        request.setAttribute("product", product);
        request.setAttribute("quantity", quantity);

        request.getRequestDispatcher("/order/orderForm.jsp").forward(request, response);
    }
}
