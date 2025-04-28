package shoppingMall.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.domain.enums.UserType;
import shoppingMall.dto.UserTypeDto;
import shoppingMall.service.UserService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        if (userService.login(userId, password)) {
            UserTypeDto userByUserId = userService.getUserByUserId(userId);
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", userByUserId.getUserId());
            session.setAttribute("userType", userByUserId.getUserType());
            session.setAttribute("userName", userByUserId.getName());
            if (userByUserId.getUserType()== UserType.User) {
                response.sendRedirect(request.getContextPath() + "/index.jsp"); // 일반사용자 로그인 성공시
            } else {
                response.sendRedirect(request.getContextPath() + "/manage/home.jsp"); // 관리자 로그인 성공시
            }
        } else {
            request.getRequestDispatcher("/user/login.jsp").forward(request, response); // 로그인 실패시
        }
    }
}
