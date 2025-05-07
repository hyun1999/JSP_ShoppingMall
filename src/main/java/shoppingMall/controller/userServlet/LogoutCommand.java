package shoppingMall.controller.userServlet.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.controller.Command;

import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 로그아웃 후 메인 페이지로 리다이렉션
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
