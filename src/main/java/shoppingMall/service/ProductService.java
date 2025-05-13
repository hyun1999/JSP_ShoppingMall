package shoppingMall.service;

import shoppingMall.dao.ProductDao;
import shoppingMall.domain.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductDao productDao = new ProductDao();

    public List<Product> getAllProducts() {
        return productDao.findAllProducts();
    }
    public List<Product> getProductsByCategoryIds(List<Integer> categoryIds) {
        return productDao.findByCategoryIds(categoryIds);
    }
    public List<Product> searchProductsByCategoryAndKeyword(List<Integer> categoryIds, String keyword) {
        return productDao.findByCategoryIdsAndKeyword(categoryIds, keyword);
    }
    public void createProduct(Product product, Connection conn) throws SQLException {
        productDao.insertProduct(product, conn);
    }

    public void updateProduct(Product product, Connection conn) throws SQLException {
        productDao.updateProduct(product, conn);
    }

    public void deleteProduct(String productId) {
        productDao.deleteProduct(productId);
    }

    public Product getProductById(String productId) {
        return productDao.findProductById(productId);
    }

    public List<Product> getProductsByCategory(int categoryId) {
        return productDao.findProductsByCategory(categoryId);
    }

}
