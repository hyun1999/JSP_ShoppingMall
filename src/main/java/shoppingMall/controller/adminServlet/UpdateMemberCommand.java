package shoppingMall.controller.adminServlet.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.service.UserService;

import java.io.IOException;

public class UpdateMemberCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String status = request.getParameter("status");
        String userType = request.getParameter("userType");

        if (userId != null) {
            userService.updateMember(userId, name, email, Status.valueOf(status), UserType.valueOf(userType));
        }

        response.sendRedirect(request.getContextPath() + "/adminPage.do");
    }
}
