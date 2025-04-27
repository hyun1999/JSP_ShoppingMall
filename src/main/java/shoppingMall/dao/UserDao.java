package shoppingMall.dao;

import shoppingMall.domain.User;
import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.dto.UserDto;
import shoppingMall.utils.JdbcDriver;

import java.sql.*;

public class UserDao {

    public boolean insert(UserDto user) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getString("user_id"));
                    user.setUserName(rs.getString("user_name"));
                    user.setPassword(rs.getString("password"));
                    user.setEncPassword(rs.getString("enc_password"));
                    user.setMobileNo(rs.getString("mobile_no"));
                    user.setEmail(rs.getString("email"));

                    String statusStr = rs.getString("status");
                    if (statusStr != null) {
                        user.setStatus(Status.valueOf(statusStr));
                    }

                    String userTypeStr = rs.getString("user_type");
                    if (userTypeStr != null) {
                        user.setUserType(UserType.valueOf(userTypeStr));
                    }

                    user.setRegisterNo(rs.getString("register_no"));

                    // LocalDateTime 변환
                    Timestamp timestamp = rs.getTimestamp("first_date");
                    if (timestamp != null) {
                        user.setFirstDate(timestamp.toLocalDateTime());
                    }

                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
