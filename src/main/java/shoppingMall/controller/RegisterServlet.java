package shoppingMall.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.exception.InvalidUserIdException;
import shoppingMall.service.UserService;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/user/registerForm.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (!userService.registerUser(request)) {
                request.setAttribute("errorMessage", "회원가입 실패. 다시 시도해 주세요.");
                request.getRequestDispatcher("/user/registerForm.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/user/registerSuccess.jsp");
            }
        } catch (InvalidUserIdException e) {
            // 사용자 ID 검증 실패시
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/user/registerForm.jsp").forward(request, response);
        }
    }
}
