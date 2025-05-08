package shoppingMall.controller.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import shoppingMall.controller.Command;
import shoppingMall.domain.Product;
import shoppingMall.service.CategoryProductMappingService;
import shoppingMall.service.ProductService;
import shoppingMall.service.CategoryService;
import shoppingMall.utils.JdbcDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@MultipartConfig
public class ManageProductCommand implements Command {
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();
    private final CategoryProductMappingService mappingService = new CategoryProductMappingService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET 방식일 경우
        if (request.getMethod().equalsIgnoreCase("GET")) {
            String noProduct = request.getParameter("noProduct");

            if (noProduct != null) {
                // 수정하려는 상품을 조회하여 selectedProduct로 전달
                Product selectedProduct = productService.getProductById(noProduct);
                request.setAttribute("selectedProduct", selectedProduct);
            }

            // 카테고리 및 상품 목록을 전달
            request.setAttribute("categoryList", categoryService.getAllCategories());
            request.setAttribute("productList", productService.getAllProducts());

            request.getRequestDispatcher("/admin/productPage.jsp").forward(request, response);
            return;
        }

        // POST 방식일 경우 (상품 등록, 수정, 삭제)
        String action = request.getParameter("action");
        if (action == null) {
            throw new ServletException("Action parameter is missing.");
        }

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

        // 파일 업로드 처리
        Part filePart = request.getPart("productImage");
        if (filePart != null && filePart.getSize() > 0) {
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String savedFileName = UUID.randomUUID().toString().replace("-", "").substring(0, 20) + fileExtension;

            if (savedFileName.length() > 30) {
                savedFileName = savedFileName.substring(0, 30);
            }

            String uploadPath = "C:\\uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            filePart.write(uploadPath + File.separator + savedFileName);
            product.setIdFile(savedFileName);
        }

        product.setNoProduct("P" + System.currentTimeMillis());
        product.setNoRegister("admin");
        product.setDaFirstDate(LocalDateTime.now());

        String categoryIdStr = request.getParameter("categoryId");

        // === 트랜잭션 시작 ===
        java.sql.Connection conn = null;
        try {
            conn = JdbcDriver.getConnection();
            conn.setAutoCommit(false); // 트랜잭션 시작

            productService.createProduct(product, conn);

            if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                int categoryId = Integer.parseInt(categoryIdStr);
                mappingService.createMapping(categoryId, product.getNoProduct(), "admin", conn);
            }

            conn.commit(); // 성공 시 커밋
        } catch (Exception e) {
            if (conn != null) {
                try { conn.rollback(); } catch (Exception rollbackEx) {}
            }
            throw new ServletException("상품 등록 트랜잭션 실패", e);
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (Exception closeEx) {}
            }
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Product product = extractProductFromRequest(request);
        String noProduct = request.getParameter("noProduct");
        product.setNoProduct(noProduct);
        product.setNoRegister("admin");
        product.setDaFirstDate(LocalDateTime.now());

        // 기존 상품 정보에서 이미지 파일이 비어있다면, 기존 파일을 유지
        Part filePart = request.getPart("productImage");
        String existingFileName = request.getParameter("existingFileName");

        if (filePart != null && filePart.getSize() > 0) {
            String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String savedFileName = UUID.randomUUID().toString().replace("-", "").substring(0, 20) + fileExtension;

            String uploadPath = "C:\\uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            filePart.write(uploadPath + File.separator + savedFileName);
            product.setIdFile(savedFileName); // 새 파일명을 설정
        } else {
            // 새 이미지가 없으면 기존 파일명을 그대로 사용
            product.setIdFile(existingFileName); // 기존 파일명 유지
        }

        productService.updateProduct(product);
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
