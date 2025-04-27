package shoppingMall.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.service.UserService;

import java.io.IOException;

@WebServlet("/check-email")
public class CheckEmailServlet extends HttpServlet {
    private UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        if (userService.validateDuplicate(userId)) {
            response.getWriter().write("사용 가능한 이메일");
        } else {
            response.getWriter().write("사용 불가능한 이메일");
        }
    }
}
