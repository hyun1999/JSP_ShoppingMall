package shoppingMall.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        String viewPage = null;

        if (path.equals("/home.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } else if (path.equals("/register.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register");
            dispatcher.forward(request, response);
        } else if (path.equals("/login.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
            dispatcher.forward(request, response);
        } else if (path.equals("/logout.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logout");
            dispatcher.forward(request, response);
        } else if (path.equals("/mypage.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/mypage");
            dispatcher.forward(request, response);
        } else if (path.equals("/editProfile.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editProfile");
            dispatcher.forward(request, response);
        } else if (path.equals("/memberDelete.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/delete");
            dispatcher.forward(request, response);
        } else if (path.equals("/adminPage.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/adminPage");
            dispatcher.forward(request, response);
        } else if (path.equals("/updateMember.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/updateMember");
            dispatcher.forward(request, response);
        } else if (path.equals("/approveUser.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/approveUser");
            dispatcher.forward(request, response);
        }else if (path.equals("/deleteMember.do")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/deleteMember");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        if (viewPage != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
        }
    }
}
