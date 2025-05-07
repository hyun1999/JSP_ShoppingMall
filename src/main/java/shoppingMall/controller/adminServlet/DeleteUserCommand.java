package shoppingMall.controller.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.service.UserService;

import java.io.IOException;

public class DeleteUserCommand implements Command {

    private final UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        if (userId != null && !userId.isEmpty()) {
            userService.deleteUserById(userId); // userId로 유저 삭제
        }

        // 삭제 후 관리자 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/adminPage.do");
    }
}
