package shoppingMall.dto;

import shoppingMall.domain.enums.UserType;

public class UserTypeDto {
    private String userId;
    private String name;
    private UserType userType;

    public UserTypeDto(String userId, String name, UserType userType) {
        this.userId = userId;
        this.name = name;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
