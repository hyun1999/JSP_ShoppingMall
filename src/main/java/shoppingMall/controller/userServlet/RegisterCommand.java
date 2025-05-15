package shoppingMall.controller.userServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.exception.InvalidUserIdException;
import shoppingMall.service.UserService;

public class RegisterCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");

        if (!userService.validateDuplicate(userId)) {
            request.setAttribute("errorMessage", "중복된 이메일입니다.");
            return "/user/register.jsp";
        }

        if (!userService.nullCheck(request)) {
            request.setAttribute("errorMessage", "모든 칸을 입력해주세요.");
            return "/user/register.jsp";
        }

        try {
            if (!userService.registerUser(request)) {
                request.setAttribute("errorMessage", "회원가입 실패. 다시 시도해 주세요.");
                return "/user/register.jsp";
            } else {
                return "redirect:/index.jsp";
            }
        } catch (InvalidUserIdException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/user/register.jsp";
        }
    }
}
