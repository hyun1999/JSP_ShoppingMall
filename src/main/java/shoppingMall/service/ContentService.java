package shoppingMall.service;

import shoppingMall.dao.ContentDao;
import shoppingMall.domain.Content;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class ContentService {
    private final ContentDao contentDAO = new ContentDao();

    // 이미지 저장 (BLOB 형태로)
    public void saveContent(Content content, InputStream fileInputStream, Connection conn) throws SQLException {
        contentDAO.insertContent(content, fileInputStream, conn);  // Connection을 DAO로 전달
    }

    // 이미지 조회
    public Content getContentById(String idFile, Connection conn) throws SQLException {
        return contentDAO.findById(idFile, conn);  // Connection을 DAO로 전달
    }
}
