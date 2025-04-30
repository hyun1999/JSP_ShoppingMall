package shoppingMall.service;

import jakarta.servlet.http.HttpServletRequest;
import shoppingMall.dao.UserDao;
import shoppingMall.domain.User;
import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.dto.RegisterDto;
import shoppingMall.dto.UserTypeDto;
import shoppingMall.exception.InvalidUserIdException;
import shoppingMall.utils.Encoder;

import java.util.List;
import java.util.regex.Pattern;

public class UserService {
    private UserDao userDao = new UserDao();

    // 이메일 형식 검증 정규식
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]{5,15}@[a-zA-Z0-9.-]{1,15}\\.[a-zA-Z]{2,}$";
    // 사용자 ID 검증 정규식 (영문자, 숫자, 길이 5~15자)
    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]{5,15}$";

    public boolean registerUser(HttpServletRequest request) throws InvalidUserIdException {
        String userId = request.getParameter("userId");
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
        User findUser= userDao.findByUserId(userId);
        if (findUser == null) return false; // user가 없으면 false처리
        String foundEncPassword = findUser.getEncPassword();
        String encodedPassword = Encoder.encode(password);
        if(foundEncPassword.equals(encodedPassword)) return true;
        else return false;
    }

    public UserTypeDto getUserByUserId(String userId){
        User foundUser = userDao.findByUserId(userId);
        return new UserTypeDto(foundUser.getUserId(),foundUser.getUserName(),
                foundUser.getPassword(), foundUser.getEmail(),
                foundUser.getMobileNo(), foundUser.getUserType(), foundUser.getStatus());
    }

    // 아이디와 패스워드 검증
    private boolean validateUserId(String userId, String password) {
        if (Pattern.matches(EMAIL_REGEX, userId)) {
            return true;
        }
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    public boolean nullCheck(HttpServletRequest request) {
        if(request.getParameter("userId") == null) return false;
        else if(request.getParameter("password") == null) return false;
        else if(request.getParameter("name") == null) return false;
        else if(request.getParameter("phone_num") == null) return false;
        else return true;
    }

    public User updateUser(HttpServletRequest request) {
        userDao.updateUser(request);
        return userDao.findByUserId(request.getParameter("userId"));
    }

    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
    }

    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    public List<User> getPendingUsers() {
        return userDao.findPendingUsers();
    }

    public void approveUser(String userId) {
        userDao.updateStatus(userId, Status.ST01);
    }

    public void updateMember(String userId, String name, String email, Status status, UserType userType) {
        userDao.updateUser(userId, name, email, status, userType);
    }
}
