package shoppingMall.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.domain.User;
import shoppingMall.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/adminPage")
public class AdminPageServlet extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> allUsers = userService.getAllUsers();
        List<User> allPendingUsers = userService.getPendingUsers();
        List<User> withdrawalUsers = userService.getWithdrawalUsers();
        request.setAttribute("allUsers", allUsers);
        request.setAttribute("allPendingUsers", allPendingUsers);
        request.setAttribute("withdrawalUsers", withdrawalUsers);
        request.getRequestDispatcher("/admin/adminPage.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        userService.updateUser(User user);
    }
}