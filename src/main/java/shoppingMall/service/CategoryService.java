package shoppingMall.service;

import shoppingMall.dao.CategoryDao;
import shoppingMall.domain.Category;

import java.util.List;

public class CategoryService {

    private final CategoryDao categoryDao = new CategoryDao();

    // 모든 카테고리 조회
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    // 카테고리 생성
    public void createCategory(Category category) {
        if (category.getParentCategoryId() == null) {
            category.setParentCategoryId(0);
        }
        categoryDao.insert(category);
    }

    // 카테고리 수정
    public void updateCategory(Category category) {
        if (category.getParentCategoryId() == null) {
            category.setParentCategoryId(0);
        }
        categoryDao.update(category);
        System.out.println("update완료");
    }

    // 카테고리 삭제
    public void deleteCategory(int id) {
        categoryDao.delete(id);
    }

    // 카테고리 ID로 조회
    public Category getCategoryById(int id) {
        return categoryDao.findById(id);
    }


}
