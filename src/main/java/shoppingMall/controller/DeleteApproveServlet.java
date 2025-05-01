package shoppingMall.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.service.UserService;

import java.io.IOException;

@WebServlet("/admin/deleteMember")
public class DeleteApproveServlet extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        userService.deleteUserById(userId);
        response.sendRedirect(request.getContextPath() + "/adminPage.do");
    }
}
