package shoppingMall.controller.adminServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shoppingMall.controller.Command;
import shoppingMall.dto.ContentStreamDto;
import shoppingMall.service.ContentService;

import java.io.InputStream;
import java.io.OutputStream;

public class ImageCommand implements Command {

    private final ContentService contentService = new ContentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String idFile = request.getParameter("idFile");

        if (idFile == null || idFile.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        try {
            ContentStreamDto dto = contentService.getImageStreamById(idFile);
            if (dto == null || dto.getInputStream() == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return null;
            }

            String ext = dto.getContent().getFileExt();
            if (ext == null || ext.isBlank()) ext = "jpeg";
            response.setContentType("image/" + ext.toLowerCase());

            try (InputStream is = dto.getInputStream(); OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[8192];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return null;
    }
}
