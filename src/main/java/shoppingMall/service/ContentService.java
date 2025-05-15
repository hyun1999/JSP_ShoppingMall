package shoppingMall.service;

import shoppingMall.dao.ContentDao;
import shoppingMall.domain.Content;
import shoppingMall.dto.ContentStreamDto;
import shoppingMall.utils.JdbcDriver;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ContentService {
    private final ContentDao contentDAO = new ContentDao();

    // 이미지 저장 (BLOB 형태로)
    public void saveContent(Content content, InputStream fileInputStream, Connection conn) throws Exception {
        contentDAO.insertContent(content, fileInputStream, conn);
    }

    public ContentStreamDto getImageStreamById(String idFile) throws Exception {
        try (Connection conn = JdbcDriver.getConnection()) {
            Content content = contentDAO.findById(idFile, conn);
            if (content == null) return null;

            String sql = "SELECT bo_save_file FROM TB_CONTENT WHERE id_file = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idFile);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        InputStream is = rs.getBinaryStream("bo_save_file");
                        return new ContentStreamDto(content, null, is);
                    }
                }
            }
        }
        return null;
    }
}
