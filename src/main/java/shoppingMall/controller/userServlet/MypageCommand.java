package shoppingMall.controller.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;

import java.io.IOException;

public class MypageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // "/mypage" 요청에 대해 mypage.jsp로 포워딩
        request.getRequestDispatcher("/user/mypage.jsp").forward(request, response);
    }
}
