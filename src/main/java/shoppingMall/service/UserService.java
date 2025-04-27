package shoppingMall.service;

import jakarta.servlet.http.HttpServletRequest;
import shoppingMall.dao.UserDao;
import shoppingMall.domain.User;
import shoppingMall.dto.RegisterDto;
import shoppingMall.exception.InvalidUserIdException;

import java.util.regex.Pattern;

public class UserService {
    private UserDao userDao = new UserDao();

    // 이메일 형식 검증 정규식
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]{5,15}@[a-zA-Z0-9.-]{1,15}\\.[a-zA-Z]{2,}$";
    // 사용자 ID 검증 정규식 (영문자, 숫자, 길이 5~15자)
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]{5,15}$";

    public boolean registerUser(HttpServletRequest request) throws InvalidUserIdException {
        String userId = request.getParameter("id");
        String password = request.getParameter("password");
        String userName = request.getParameter("name");
        String phoneNum = request.getParameter("phone_num");
        String userType = request.getParameter("user_type");

        if (!validateUserId(userId, password)) {
            throw new InvalidUserIdException("이메일과 비밀번호를 다시 설정해주세요.");
        }

        RegisterDto registerDto = new RegisterDto(userId, password, userName, phoneNum, userType);
        return userDao.insertUser(registerDto);
    }

    // 이메일 중복 검증
    public boolean validateDuplicate(String userId) {
        User user = userDao.findByUserId(userId);
        return user == null;
    }

    public boolean login(String userId, String password) {
//        User user = userDao.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            return user;
//        }
//        return null;
        return true;
    }
    // 아이디와 패스워드 검증
    private boolean validateUserId(String userId, String password) {
        if (Pattern.matches(EMAIL_REGEX, userId)) {
            return true;
        }
        return Pattern.matches(PASSWORD_REGEX, password);
    }
}
