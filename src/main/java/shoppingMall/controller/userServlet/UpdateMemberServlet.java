package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.service.UserService;

import java.io.IOException;

@WebServlet("/admin/updateMember")
public class UpdateMemberServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String status = request.getParameter("status");
        String userType = request.getParameter("userType");

        if (userId != null) {
            userService.updateMember(userId, name, email, Status.valueOf(status), UserType.valueOf(userType));
        }

        response.sendRedirect(request.getContextPath() + "/adminPage.do");
    }
}
