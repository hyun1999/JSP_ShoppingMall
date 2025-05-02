package shoppingMall.dao;

import jakarta.servlet.http.HttpServletRequest;
import shoppingMall.domain.User;
import shoppingMall.domain.enums.Status;
import shoppingMall.domain.enums.UserType;
import shoppingMall.dto.RegisterDto;
import shoppingMall.utils.JdbcDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void updateUser(HttpServletRequest request) {
        String sql = "UPDATE tb_user SET nm_user = ?, nm_email = ?, no_mobile = ? WHERE id_user = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, request.getParameter("userName"));
            ps.setString(2, request.getParameter("email"));
            ps.setString(3, request.getParameter("mobile"));
            ps.setString(4, request.getParameter("userId"));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String userId) {
        String sql = "DELETE FROM tb_user WHERE id_user = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findPendingUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM tb_user WHERE ST_STATUS = 'ST00'";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
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
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM tb_user";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
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
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findWithdrawalUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM tb_user WHERE st_status = 'ST03'";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
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
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void updateStatus(String userId, Status newStatus) {
        String sql = "UPDATE tb_user SET ST_STATUS = ? WHERE ID_USER = ?";
        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newStatus.name());
            ps.setString(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(String userId, String name, String email, Status status, UserType userType) {
        String sql = "UPDATE tb_user SET NM_USER = ?, NM_EMAIL = ?, ST_STATUS = ?, CD_USER_TYPE = ? WHERE ID_USER = ?";

        try (Connection conn = JdbcDriver.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, status.name());
            ps.setString(4, userType.name());
            ps.setString(5, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
