package shoppingMall.service;

import shoppingMall.dao.UserDao;
import shoppingMall.domain.User;
import shoppingMall.dto.UserDto;

public class UserService {
    private UserDao userDao = new UserDao();

    public boolean registerUser(UserDto user) {
        return userDao.insert(user);
    }

    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
