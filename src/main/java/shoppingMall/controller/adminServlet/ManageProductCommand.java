package shoppingMall.controller.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import shoppingMall.controller.Command;
import shoppingMall.domain.Content;
import shoppingMall.domain.Product;
import shoppingMall.service.*;

import shoppingMall.utils.JdbcDriver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

@MultipartConfig
public class ManageProductCommand implements Command {
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();
    private final CategoryProductMappingService mappingService = new CategoryProductMappingService();
    private final ContentService contentService = new ContentService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            String noProduct = request.getParameter("noProduct");

            if (noProduct != null) {
                Product selectedProduct = productService.getProductById(noProduct);
                request.setAttribute("selectedProduct", selectedProduct);
            }

            request.setAttribute("categoryList", categoryService.getAllCategories());
            request.setAttribute("productList", productService.getAllProducts());

            request.getRequestDispatcher("/admin/productPage.jsp").forward(request, response);
            return;
        }

        String action = request.getParameter("action");
        if (action == null) throw new ServletException("Action parameter is missing.");

        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                throw new ServletException("Invalid or missing action");
        }

        response.sendRedirect("manageProduct.do");
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Product product = extractProductFromRequest(request);
        product.setNoProduct("P" + System.currentTimeMillis());
        product.setNoRegister("admin");
        product.setDaFirstDate(LocalDateTime.now());

        String categoryIdStr = request.getParameter("categoryId");

        Connection conn = null;
        try {
            conn = JdbcDriver.getConnection();
            conn.setAutoCommit(false);

            // 파일 업로드 처리 (파일을 로컬에 저장하지 않고 DB에 저장)
            Part filePart = request.getPart("productImage");
            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString().replace("-", "").substring(0, 20) + fileExtension;

                // InputStream을 사용하여 파일을 DB에 저장
                try (InputStream fileInputStream = filePart.getInputStream()) {
                    Content content = new Content();
                    content.setIdFile(savedFileName);
                    content.setOriginalFileName(originalFileName);
                    content.setSavedFileName(savedFileName);
                    content.setFileExt(fileExtension.replace(".", ""));
                    content.setFileType("IMG");
                    content.setServiceId("PRODUCT");
                    content.setNoRegister("admin");
                    content.setFirstDate(LocalDateTime.now());

                    contentService.saveContent(content, fileInputStream, conn); // Connection을 전달
                    product.setIdFile(savedFileName);
                }
            }

            productService.createProduct(product, conn);

            if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                int categoryId = Integer.parseInt(categoryIdStr);
                mappingService.createMapping(categoryId, product.getNoProduct(), "admin", conn);
            }

            conn.commit();
        } catch (Exception e) {
            if (conn != null) try { conn.rollback(); } catch (Exception ignore) {}
            throw new ServletException("상품 등록 실패", e);
        } finally {
            if (conn != null) try { conn.close(); } catch (Exception ignore) {}
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Product product = extractProductFromRequest(request);
        product.setNoProduct(request.getParameter("noProduct"));
        product.setNoRegister("admin");
        product.setDaFirstDate(LocalDateTime.now());

        Part filePart = request.getPart("productImage");
        String existingFileName = request.getParameter("existingFileName");

        Connection conn = null;
        try {
            conn = JdbcDriver.getConnection();
            conn.setAutoCommit(false);

            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString().replace("-", "").substring(0, 20) + fileExtension;

                // InputStream을 사용하여 파일을 DB에 저장
                try (InputStream fileInputStream = filePart.getInputStream()) {
                    Content content = new Content();
                    content.setIdFile(savedFileName);
                    content.setOriginalFileName(originalFileName);
                    content.setSavedFileName(savedFileName);
                    content.setFileExt(fileExtension.replace(".", ""));
                    content.setFileType("IMG");
                    content.setServiceId("PRODUCT");
                    content.setNoRegister("admin");
                    content.setFirstDate(LocalDateTime.now());

                    contentService.saveContent(content, fileInputStream, conn); // Connection을 전달
                    product.setIdFile(savedFileName);
                }
            } else {
                product.setIdFile(existingFileName);
            }

            productService.updateProduct(product, conn);
            conn.commit();
        } catch (Exception e) {
            if (conn != null) try { conn.rollback(); } catch (Exception ignore) {}
            throw new ServletException("상품 수정 실패", e);
        } finally {
            if (conn != null) try { conn.close(); } catch (Exception ignore) {}
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String noProduct = request.getParameter("noProduct");
        productService.deleteProduct(noProduct);
    }

    private Product extractProductFromRequest(HttpServletRequest request) {
        Product p = new Product();
        p.setNmProduct(request.getParameter("nmProduct"));
        p.setNmDetailExplain(request.getParameter("nmDetailExplain"));
        p.setDtStartDate(request.getParameter("dtStartDate"));
        p.setDtEndDate(request.getParameter("dtEndDate"));
        p.setQtCustomerPrice(Integer.parseInt(request.getParameter("qtCustomerPrice")));
        p.setQtSalePrice(Integer.parseInt(request.getParameter("qtSalePrice")));
        p.setQtStock(Integer.parseInt(request.getParameter("qtStock")));
        p.setQtDeliveryFee(Integer.parseInt(request.getParameter("qtDeliveryFee")));
        return p;
    }
}
