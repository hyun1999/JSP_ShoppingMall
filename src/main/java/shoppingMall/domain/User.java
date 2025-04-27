package shoppingMall.domain;

import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;

import java.time.LocalDateTime;

public class User {
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

    public User() {

    }
    public User(String userId, String userName, String password, String encPassword, String mobileNo, String email, Status status, UserType userType, String registerNo, LocalDateTime firstDate) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.encPassword = encPassword;
        this.mobileNo = mobileNo;
        this.email = email;
        this.status = status;
        this.userType = userType;
        this.registerNo = registerNo;
        this.firstDate = firstDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEncPassword(String encPassword) {
        this.encPassword = encPassword;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public void setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }



    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEncPassword() {
        return encPassword;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public Status getStatus() {
        return status;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }
}
