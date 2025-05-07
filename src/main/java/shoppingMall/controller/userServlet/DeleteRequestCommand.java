package shoppingMall.controller.userServlet.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.service.UserService;

import java.io.IOException;

public class DeleteRequestCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId != null) {
            userService.stopUser(userId);  // 사용자 삭제 요청 처리
            request.getSession().invalidate();  // 세션 무효화
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");  // 리다이렉트
    }
}
