package shoppingMall.service;

import shoppingMall.dao.ProductDao;
import shoppingMall.domain.Product;

import java.util.List;

public class ProductService {

    private final ProductDao productDao = new ProductDao();

    // 모든 상품 조회
    public List<Product> getAllProducts() {
        return productDao.findAllProducts();
    }

    // 카테고리 생성
//    public void createCategory(Category category) {
//        if (category.getParentCategoryId() == null) {
//            category.setParentCategoryId(0);
//        }
//        categoryDao.insert(category);
//    }
//
//    // 카테고리 수정
//    public void updateCategory(Category category) {
//        if (category.getParentCategoryId() == null) {
//            category.setParentCategoryId(0);
//        }
//        categoryDao.update(category);
//    }
//
//    // 카테고리 삭제
//    public void deleteCategory(int id) {
//        categoryDao.delete(id);
//    }
//
//    // 카테고리 ID로 조회
//    public Category getCategoryById(int id) {
//        return categoryDao.findById(id);
//    }
}
