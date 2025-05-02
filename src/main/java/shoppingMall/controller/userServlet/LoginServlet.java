package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.dto.UserTypeDto;
import shoppingMall.service.UserService;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        if (userService.login(userId, password)) {
            UserTypeDto userByUserId = userService.getUserByUserId(userId);
            // ST00(승인 대기 상태)인 경우 로그인 거부
            if (userByUserId.getUserStatus().equals(Status.ST00)) {

                request.setAttribute("error", "관리자 승인 후 로그인 가능합니다.");
                request.getRequestDispatcher("/user/login.jsp").forward(request, response);
                return;
            }

            // ST02(승인 대기 상태)인 경우 로그인 거부
            if (userByUserId.getUserStatus().equals(Status.ST02)) {
                request.setAttribute("error", "일시정지 상태입니다. 관리자에게 요청하세요.");
                request.getRequestDispatcher("/user/login.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("userId", userByUserId.getUserId());
            session.setAttribute("userType", userByUserId.getUserType());
            session.setAttribute("email", userByUserId.getEmail());
            session.setAttribute("userName", userByUserId.getUserName());

            if (userByUserId.getUserType() == UserType.User) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/adminPage.do");
            }
        } else {
            request.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        }
    }
}
