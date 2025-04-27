package shoppingMall.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.dao.UserDao;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/user/registerForm.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼 데이터 받기
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // DB 저장 로직 (간단 예시)
        UserDao userDao = new UserDao();
//        userDao.insert(new User(username, password));

        // 회원가입 완료 후 로그인 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/registerSuccess.jsp");
    }
}
