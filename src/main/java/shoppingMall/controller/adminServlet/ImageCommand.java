package shoppingMall.controller.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.domain.Content;
import shoppingMall.service.ContentService;
import shoppingMall.utils.JdbcDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

public class ImageCommand implements Command {
    private final ContentService contentService = new ContentService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idFile = request.getParameter("idFile");

        if (idFile == null || idFile.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing idFile parameter");
            return;
        }

        try (Connection conn = JdbcDriver.getConnection()) {
            Content content = contentService.getContentById(idFile, conn);

            if (content == null || content.getSavedFileName() == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
                return;
            }

            String uploadPath = content.getFilePath();
            String savedFileName = content.getSavedFileName();
            File imageFile = new File(uploadPath, savedFileName);
            if (!imageFile.exists()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "File does not exist on server");
                return;
            }

            response.setContentType("image/" + content.getFileExt());
            response.setContentLengthLong(imageFile.length());

            try (FileInputStream fis = new FileInputStream(imageFile);
                 OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[8192];
                int read;
                while ((read = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
            }

        } catch (Exception e) {
            throw new ServletException("Failed to load image", e);
        }
    }
}
