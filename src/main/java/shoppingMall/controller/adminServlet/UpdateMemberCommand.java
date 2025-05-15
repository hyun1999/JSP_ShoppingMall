package shoppingMall.controller.adminServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.service.UserService;

public class UpdateMemberCommand implements Command {

    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String status = request.getParameter("status");
        String userType = request.getParameter("userType");

        if (userId != null) {
            userService.updateMember(
                    userId,
                    name,
                    email,
                    Status.valueOf(status),
                    UserType.valueOf(userType)
            );
        }

        return "redirect:/adminPage.do";
    }
}
