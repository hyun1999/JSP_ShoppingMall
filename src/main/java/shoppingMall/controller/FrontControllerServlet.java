package shoppingMall.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {

    private final Map<String, String> routeMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        routeMap.put("/home.do", "/index.jsp");
        routeMap.put("/register.do", "/register");
        routeMap.put("/login.do", "/login");
        routeMap.put("/logout.do", "/logout");
        routeMap.put("/mypage.do", "/mypage");
        routeMap.put("/editProfile.do", "/editProfile");
        routeMap.put("/memberDelete.do", "/delete");
        routeMap.put("/adminPage.do", "/admin/adminPage");
        routeMap.put("/updateMember.do", "/admin/updateMember");
        routeMap.put("/approveUser.do", "/admin/approveUser");
        routeMap.put("/deleteMember.do", "/admin/deleteMember");
        routeMap.put("/manageCategory.do", "/admin/manageCategory");
        routeMap.put("/manageProduct.do", "/admin/manageProduct");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());
        String viewPage = routeMap.get(path);

        if (viewPage != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
