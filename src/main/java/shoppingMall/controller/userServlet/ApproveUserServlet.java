package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import shoppingMall.service.UserService;

import java.io.IOException;

@WebServlet("/admin/approveUser")
public class ApproveUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        if (userId != null && !userId.isEmpty()) {
            userService.approveUser(userId); // ST00 → ST01 로 상태 변경
        }

        response.sendRedirect(request.getContextPath() + "/adminPage.do");
    }
}
