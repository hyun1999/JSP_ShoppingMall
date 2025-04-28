package shoppingMall.dto;

import shoppingMall.domain.enums.UserType;

public class UserDto {
    private String userId;
    private String password;

    public UserDto(String userId, String userName, UserType userType) {}

    public UserDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
