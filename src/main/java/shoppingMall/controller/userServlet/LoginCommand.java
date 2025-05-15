package shoppingMall.controller.userServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.controller.Command;
import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.dto.UserTypeDto;
import shoppingMall.service.UserService;

public class LoginCommand implements Command {
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            return "redirect:/user/login.jsp";
        }

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if (userService.login(userId, password)) {
            UserTypeDto userByUserId = userService.getUserByUserId(userId);

            if (userByUserId.getUserStatus() == Status.ST00) {
                session.setAttribute("error", "관리자 승인 후 로그인 가능합니다.");
                return "redirect:/user/login.jsp";
            }

            if (userByUserId.getUserStatus() == Status.ST02) {
                session.setAttribute("error", "일시정지 상태입니다. 관리자에게 요청하세요.");
                return "redirect:/user/login.jsp";
            }

            session.setAttribute("userId", userByUserId.getUserId());
            session.setAttribute("userType", userByUserId.getUserType());
            session.setAttribute("email", userByUserId.getEmail());
            session.setAttribute("userName", userByUserId.getUserName());

            if (userByUserId.getUserType() == UserType.User) {
                return "redirect:/home.do";
            } else {
                return "redirect:/adminPage.do";
            }
        } else {
            session.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/user/login.jsp";
        }
    }
}
