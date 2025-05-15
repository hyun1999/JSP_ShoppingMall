package shoppingMall.controller.adminServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.User;
import shoppingMall.service.UserService;

import java.util.List;

public class AdminPageCommand implements Command {

    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> allUsers = userService.getAllUsers();
        List<User> allPendingUsers = userService.getPendingUsers();
        List<User> withdrawalUsers = userService.getWithdrawalUsers();

        request.setAttribute("allUsers", allUsers);
        request.setAttribute("allPendingUsers", allPendingUsers);
        request.setAttribute("withdrawalUsers", withdrawalUsers);

        return "/admin/adminPage.jsp";
    }
}
