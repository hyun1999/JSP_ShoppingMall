package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.controller.Command;
import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.dto.UserTypeDto;
import shoppingMall.service.UserService;

import java.io.IOException;

public class LoginCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
            return;
        }
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if (userService.login(userId, password)) {
            UserTypeDto userByUserId = userService.getUserByUserId(userId);

            if (userByUserId.getUserStatus().equals(Status.ST00)) {
                session.setAttribute("error", "관리자 승인 후 로그인 가능합니다.");
                response.sendRedirect(request.getContextPath() + "/user/login.jsp");
                return;
            }

            if (userByUserId.getUserStatus().equals(Status.ST02)) {
                session.setAttribute("error", "일시정지 상태입니다. 관리자에게 요청하세요.");
                response.sendRedirect(request.getContextPath() + "/user/login.jsp");
                return;
            }

            session.setAttribute("userId", userByUserId.getUserId());
            session.setAttribute("userType", userByUserId.getUserType());
            session.setAttribute("email", userByUserId.getEmail());
            session.setAttribute("userName", userByUserId.getUserName());

            if (userByUserId.getUserType() == UserType.User) {
                response.sendRedirect(request.getContextPath() + "/home.do");
            } else {
                response.sendRedirect(request.getContextPath() + "/adminPage.do");
            }
        } else {
            session.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            response.sendRedirect(request.getContextPath() + "/user/login.jsp");
        }
    }

}
