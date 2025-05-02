package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.exception.InvalidUserIdException;
import shoppingMall.service.UserService;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/user/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 이메일 중복 체크
        String userId = request.getParameter("userId");

        // 이메일이 중복된 경우
        if (!userService.validateDuplicate(userId)) {
            request.setAttribute("errorMessage", "중복된 이메일입니다.");
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            return;
        }

        // 빈값이 들어올 경우
        if (!userService.nullCheck(request)) {
            request.setAttribute("errorMessage", "모든 칸을 입력해주세요.");
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            return;
        }

        // 이메일이 사용 가능한 경우에만 회원가입 진행
        try {
            if (!userService.registerUser(request)) {
                request.setAttribute("errorMessage", "회원가입 실패. 다시 시도해 주세요.");
                request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (InvalidUserIdException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
        }
    }
}