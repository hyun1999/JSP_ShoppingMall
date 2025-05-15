package shoppingMall.dao;

import shoppingMall.domain.Content;

import java.io.InputStream;
import java.sql.*;

public class ContentDao {

    public void insertContent(Content content, InputStream fileInputStream, Connection conn) throws SQLException {
        String sql = "INSERT INTO TB_CONTENT (" +
                "id_file, nm_org_file, nm_save_file, nm_file_path, bo_save_file, " +
                "nm_file_ext, cd_file_type, id_service, no_register, da_first_date" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, content.getIdFile());
            pstmt.setString(2, content.getOriginalFileName());
            pstmt.setString(3, content.getSavedFileName());
            pstmt.setString(4, content.getFilePath());
            pstmt.setBinaryStream(5, fileInputStream); // BLOB
            pstmt.setString(6, content.getFileExt());
            pstmt.setString(7, content.getFileType());
            pstmt.setString(8, content.getServiceId());
            pstmt.setString(9, content.getNoRegister());
            pstmt.setTimestamp(10, Timestamp.valueOf(content.getFirstDate()));
            pstmt.executeUpdate();
        }
    }

    public Content findById(String idFile, Connection conn) throws SQLException {
        String sql = "SELECT * FROM TB_CONTENT WHERE id_file = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, idFile);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Content content = new Content();
                    content.setIdFile(rs.getString("id_file"));
                    content.setOriginalFileName(rs.getString("nm_org_file"));
                    content.setSavedFileName(rs.getString("nm_save_file"));
                    content.setFilePath(rs.getString("nm_file_path"));
                    content.setFileExt(rs.getString("nm_file_ext"));
                    content.setFileType(rs.getString("cd_file_type"));
                    content.setServiceId(rs.getString("id_service"));
                    content.setNoRegister(rs.getString("no_register"));
                    Timestamp ts = rs.getTimestamp("da_first_date");
                    if (ts != null) {
                        content.setFirstDate(ts.toLocalDateTime());
                    }
                    return content;
                }
            }
        }
        return null;
    }
}
