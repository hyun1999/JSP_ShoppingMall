package shoppingMall.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.adminServlet.*;
import shoppingMall.controller.adminServlet.command.UpdateMemberCommand;
import shoppingMall.controller.orderServlet.OrderFormCommand;
import shoppingMall.controller.orderServlet.OrderSuccessCommand;
import shoppingMall.controller.orderServlet.SubmitOrderCommand;
import shoppingMall.controller.userServlet.*;
import shoppingMall.controller.userServlet.command.DeleteRequestCommand;
import shoppingMall.controller.userServlet.command.EditProfileCommand;
import shoppingMall.controller.userServlet.command.LogoutCommand;

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

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        Command command = routeMap.get(path);

        if (command != null) {
            command.execute(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
