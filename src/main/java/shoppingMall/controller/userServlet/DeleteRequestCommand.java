package shoppingMall.controller.userServlet.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.service.UserService;

public class DeleteRequestCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId != null) {
            userService.stopUser(userId);
            request.getSession().invalidate();
        }

        return "redirect:/index.jsp";
    }
}
