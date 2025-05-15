package shoppingMall.controller.adminServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.service.UserService;

public class ApproveUserCommand implements Command {

    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");

        if (userId != null && !userId.isEmpty()) {
            userService.approveUser(userId);
        }

        return "redirect:/adminPage.do";
    }
}
