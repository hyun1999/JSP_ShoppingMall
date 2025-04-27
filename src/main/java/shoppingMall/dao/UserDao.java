package shoppingMall.dao;

import shoppingMall.domain.User;
import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.dto.RegisterDto;
import shoppingMall.utils.JdbcDriver;

import java.sql.*;

public class UserDao {

    public boolean insertUser(RegisterDto user) {
        String sql = "    INSERT INTO tb_user (\n" +
                     "        id_user, nm_user, nm_paswd, nm_enc_paswd,\n" +
                     "        no_mobile, nm_email, st_status, cd_user_type, no_register, da_first_date\n" +
                     "    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)\n";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEncPassword());
            ps.setString(5, user.getMobileNo());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getStatus().name());
            ps.setString(8, user.getUserType().name());
            ps.setString(9, user.getRegisterNo());
            ps.setTimestamp(10, Timestamp.valueOf(user.getFirstDate()));
            int rows = ps.executeUpdate();
            System.out.println("rows = " + rows);
            System.out.println("완료");
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User findByUserId(String userId) {
        String sql = "SELECT * FROM tb_user WHERE id_user = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getString("ID_USER"));
                    user.setUserName(rs.getString("NM_USER"));
                    user.setPassword(rs.getString("NM_PASWD"));
                    user.setEncPassword(rs.getString("NM_ENC_PASWD"));
                    user.setMobileNo(rs.getString("NO_MOBILE"));
                    user.setEmail(rs.getString("NM_EMAIL"));

                    String statusStr = rs.getString("ST_STATUS");
                    if (statusStr != null) {
                        user.setStatus(Status.valueOf(statusStr));
                    }

                    String userTypeStr = rs.getString("CD_USER_TYPE");
                    if (userTypeStr != null) {
                        user.setUserType(UserType.valueOf(userTypeStr));
                    }

                    user.setRegisterNo(rs.getString("NO_REGISTER"));

                    Timestamp timestamp = rs.getTimestamp("DA_FIRST_DATE");
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
