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
import java.time.LocalDateTime;
import java.util.UUID;

@MultipartConfig
public class ManageProductCommand implements Command {

    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();
    private final CategoryProductMappingService mappingService = new CategoryProductMappingService();
    private final ContentService contentService = new ContentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            String noProduct = request.getParameter("noProduct");

            if (noProduct != null) {
                Product selectedProduct = productService.getProductById(noProduct);
                request.setAttribute("selectedProduct", selectedProduct);
            }

            request.setAttribute("categoryList", categoryService.getAllCategories());
            request.setAttribute("productList", productService.getAllProducts());

            return "/admin/productPage.jsp";
        }

        String action = request.getParameter("action");
        if (action == null) throw new ServletException("Action parameter is missing.");

        switch (action) {
            case "create":
                createProduct(request);
                break;
            case "update":
                updateProduct(request);
                break;
            case "delete":
                deleteProduct(request);
                break;
            default:
                throw new ServletException("Invalid or missing action");
        }

        return "redirect:/manageProduct.do";
    }

    private void createProduct(HttpServletRequest request) throws IOException, ServletException {
        Product product = extractProductFromRequest(request);
        product.setNoProduct(String.valueOf(System.currentTimeMillis()));
        product.setNoRegister("admin");
        product.setDaFirstDate(LocalDateTime.now());

        String categoryIdStr = request.getParameter("categoryId");

        try (Connection conn = JdbcDriver.getConnection()) {
            conn.setAutoCommit(false);

            Part filePart = request.getPart("productImage");
            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString().replace("-", "").substring(0, 20) + fileExtension;

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

                    contentService.saveContent(content, fileInputStream, conn);
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
            throw new ServletException("상품 등록 실패", e);
        }
    }

    private void updateProduct(HttpServletRequest request) throws IOException, ServletException {
        Product product = extractProductFromRequest(request);
        product.setNoProduct(request.getParameter("noProduct"));
        product.setNoRegister("admin");
        product.setDaFirstDate(LocalDateTime.now());

        Part filePart = request.getPart("productImage");
        String existingFileName = request.getParameter("existingFileName");

        try (Connection conn = JdbcDriver.getConnection()) {
            conn.setAutoCommit(false);

            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString().replace("-", "").substring(0, 20) + fileExtension;

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

                    contentService.saveContent(content, fileInputStream, conn);
                    product.setIdFile(savedFileName);
                }
            } else {
                product.setIdFile(existingFileName);
            }

            productService.updateProduct(product, conn);
            conn.commit();
        } catch (Exception e) {
            throw new ServletException("상품 수정 실패", e);
        }
    }

    private void deleteProduct(HttpServletRequest request) throws ServletException {
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
