package shoppingMall.controller.userServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.controller.Command;
import shoppingMall.domain.User;
import shoppingMall.dto.UserTypeDto;
import shoppingMall.service.UserService;

public class EditProfileCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = (String) request.getSession().getAttribute("userId");

        if (userId == null) {
            return "redirect:/login.do";
        }

        if (request.getMethod().equals("GET")) {
            UserTypeDto user = userService.getUserByUserId(userId);
            request.setAttribute("user", user);
            return "/user/editProfile.jsp";
        }

        if (request.getMethod().equals("POST")) {
            User updatedUser = userService.updateUser(request);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.setAttribute("userId", updatedUser.getUserId());
                session.setAttribute("userType", updatedUser.getUserType());
                session.setAttribute("email", updatedUser.getEmail());
                session.setAttribute("userName", updatedUser.getUserName());
            }
            return "redirect:/mypage.do";
        }

        return null;
    }
}
