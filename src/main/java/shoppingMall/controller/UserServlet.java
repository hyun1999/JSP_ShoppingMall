package shoppingMall.controller;

import jakarta.servlet.http.HttpServlet;
import shoppingMall.service.UserService;

public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();

}

