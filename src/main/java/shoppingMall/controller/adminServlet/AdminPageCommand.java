package shoppingMall.controller.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.User;
import shoppingMall.service.UserService;

import java.io.IOException;
import java.util.List;

public class AdminPageCommand implements Command {

    private final UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 관리자 페이지 처리
        List<User> allUsers = userService.getAllUsers();
        List<User> allPendingUsers = userService.getPendingUsers();
        List<User> withdrawalUsers = userService.getWithdrawalUsers();

        request.setAttribute("allUsers", allUsers);
        request.setAttribute("allPendingUsers", allPendingUsers);
        request.setAttribute("withdrawalUsers", withdrawalUsers);

        request.getRequestDispatcher("/admin/adminPage.jsp").forward(request, response);
    }
}
