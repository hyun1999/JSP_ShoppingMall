package shoppingMall.controller.displayCategoryServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.service.CategoryProductMappingService;

public class UnmapProductFromCategoryCommand implements Command {

    private final CategoryProductMappingService mappingService = new CategoryProductMappingService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String productId = request.getParameter("productId");

        try {
            mappingService.deleteMapping(categoryId, productId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/mappedProducts.do?categoryId=" + categoryId;
    }
}
