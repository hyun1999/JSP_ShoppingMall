package shoppingMall.controller.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.service.UserService;

import java.io.IOException;

public class ApproveUserCommand implements Command {

    private final UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        if (userId != null && !userId.isEmpty()) {
            userService.approveUser(userId); // ST00 → ST01 로 상태 변경
        }

        response.sendRedirect(request.getContextPath() + "/adminPage.do");
    }
}
