package shoppingMall.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.service.UserService;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteMemberServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId != null) {
            userService.deleteUser(userId);
            request.getSession().invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
