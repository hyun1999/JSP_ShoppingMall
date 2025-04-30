package shoppingMall.dto;

import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;

public class UserTypeDto {
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String mobileNo;
    private UserType userType;
    private Status userStatus;

    public UserTypeDto() {
    }

    public UserTypeDto(String userId, String userName, String password, String email, String mobileNo, UserType userType, Status userStatus) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;
        this.userType = userType;
        this.userStatus = userStatus;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Status getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Status userStatus) {
        this.userStatus = userStatus;
    }
}
