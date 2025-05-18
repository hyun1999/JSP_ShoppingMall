package shoppingMall.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.adminServlet.*;
import shoppingMall.controller.cart.*;
import shoppingMall.controller.displayCategoryServlet.*;
import shoppingMall.controller.orderServlet.*;
import shoppingMall.controller.userServlet.*;
import shoppingMall.controller.userServlet.command.DeleteRequestCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {

    private final Map<String, Command> routeMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        routeMap.put("/home.do", new HomeCommand());
        routeMap.put("/register.do", new RegisterCommand());
        routeMap.put("/login.do", new LoginCommand());
        routeMap.put("/logout.do", new LogoutCommand());
        routeMap.put("/mypage.do", new MypageCommand());
        routeMap.put("/editProfile.do", new EditProfileCommand());
        routeMap.put("/memberDelete.do", new DeleteRequestCommand());
        routeMap.put("/adminPage.do", new AdminPageCommand());
        routeMap.put("/updateMember.do", new UpdateMemberCommand());
        routeMap.put("/approveUser.do", new ApproveUserCommand());
        routeMap.put("/deleteMember.do", new DeleteUserCommand());
        routeMap.put("/manageCategory.do", new ManageCategoryCommand());
        routeMap.put("/manageProduct.do", new ManageProductCommand());
        routeMap.put("/image.do", new ImageCommand());
        routeMap.put("/productByCategory.do", new ProductByCategoryCommand());
        routeMap.put("/productDetail.do", new ProductDetailCommand());
        routeMap.put("/orderForm.do", new OrderFormCommand());
        routeMap.put("/submitOrder.do", new SubmitOrderCommand());
        routeMap.put("/orderSuccess.do", new OrderSuccessCommand());
        // 장바구니 Routing
        routeMap.put("/addToCart.do", new AddToCartCommand());
        routeMap.put("/viewCart.do", new ViewCartCommand());
        routeMap.put("/updateCartItem.do", new UpdateCartItemCommand());
        routeMap.put("/deleteCartItem.do", new DeleteCartItemCommand());
        routeMap.put("/clearCart.do", new ClearCartCommand());
        routeMap.put("/orderSelected.do", new OrderSelectedCommand());
        routeMap.put("/submitSelectedOrder.do", new SubmitSelectedOrderCommand());
        routeMap.put("/deleteSelected.do", new DeleteSelectedCartItemsCommand());
        routeMap.put("/orderList.do", new OrderListCommand());
        routeMap.put("/cancelOrder.do", new CancelOrderCommand());
        routeMap.put("/orderDetail.do", new OrderDetailCommand());
        // 전시 카테고리 관리
        routeMap.put("/manageDisplayCategory.do", new DisplayCategoryListCommand());
        routeMap.put("/addDisplayCategory.do", new DisplayCategoryInsertCommand());
        routeMap.put("/updateDisplayCategory.do", new DisplayCategoryUpdateCommand());
        routeMap.put("/mapProductToCategoryPage.do", new ProductCategoryMappingPageCommand());
        routeMap.put("/mapProductToCategory.do", new ProductCategoryMappingInsertCommand());
        routeMap.put("/deleteDisplayCategory.do", new DisplayCategoryDeleteCommand());
        routeMap.put("/mappedProducts.do", new MappedProductsCommand());
        routeMap.put("/unmapProductFromCategory.do", new UnmapProductFromCategoryCommand());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        Command command = routeMap.get(path);
        if (command != null) {
            String url = command.execute(request, response);
            if (url != null) {
                if (url.startsWith("redirect:")) {
                    response.sendRedirect(contextPath + url.substring("redirect:".length()));
                } else {
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
