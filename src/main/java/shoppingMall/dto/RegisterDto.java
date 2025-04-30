package shoppingMall.dto;

import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.utils.Encoder;

import java.time.LocalDateTime;

public class RegisterDto {
    private String userId; // 이메일 주소
    private String userName;
    private String password;
    private String encPassword;
    private String mobileNo;
    private String email;
    private Status status; // enum
    private UserType userType; // enum
    private String registerNo; // 최초 등록자 ID
    private LocalDateTime firstDate; // 최초 등록 일시

    public RegisterDto(String userId, String password, String userName, String phoneNum, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.encPassword = Encoder.encode(password);
        this.mobileNo = phoneNum;
        this.email = userId;
        this.status = Status.ST00;
        this.userType = userType.equals("user") ? UserType.User : UserType.Admn;
        this.registerNo = "ADMIN";
        this.firstDate = LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncPassword() {
        return encPassword;
    }

    public void setEncPassword(String encPassword) {
        this.encPassword = encPassword;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }
}
