package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.domain.User;
import shoppingMall.dto.UserTypeDto;
import shoppingMall.service.UserService;

import java.io.IOException;

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("login.do");
            return;
        }
        UserService userService = new UserService();
        UserTypeDto user = userService.getUserByUserId(userId);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/user/editProfile.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User updatedUser = userService.updateUser(request);
        // 세션 정보 업데이트
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