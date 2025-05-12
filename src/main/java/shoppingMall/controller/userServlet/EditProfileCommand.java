package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.controller.Command;
import shoppingMall.domain.User;
import shoppingMall.dto.UserTypeDto;
import shoppingMall.service.UserService;

import java.io.IOException;

public class EditProfileCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.do");
            return;
        }

        // doGet 부분 처리 (프로필 수정 페이지로 이동)
        if (request.getMethod().equals("GET")) {
            UserTypeDto user = userService.getUserByUserId(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/user/editProfile.jsp").forward(request, response);
        }

        else if (request.getMethod().equals("POST")) {
            User updatedUser = userService.updateUser(request);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.setAttribute("userId", updatedUser.getUserId());
                session.setAttribute("userType", updatedUser.getUserType());
                session.setAttribute("email", updatedUser.getEmail());
                session.setAttribute("userName", updatedUser.getUserName());
            }
            response.sendRedirect("mypage.do");
        }
    }
}
