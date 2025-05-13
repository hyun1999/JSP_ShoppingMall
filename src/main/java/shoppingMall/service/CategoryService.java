package shoppingMall.service;

import shoppingMall.dao.CategoryDao;
import shoppingMall.domain.Category;

import java.util.List;

public class CategoryService {

    private final CategoryDao categoryDao = new CategoryDao();

    public List<Category> getAllCategories() {
        List<Category> list = categoryDao.findAll();
        for (Category cat : list) {
            cat.setLeaf(!hasChildCategories(cat.getCategoryId()));
        }
        return list;
    }

    public void createCategory(Category category) {
        if (category.getParentCategoryId() == null) {
            category.setParentCategoryId(0);
        }

        // 자동 fullCategoryName 생성
        int parentId = category.getParentCategoryId();
        String fullName = category.getName();

        if (parentId != 0) {
            Category parent = getCategoryById(parentId);
            if (parent != null) {
                fullName = parent.getFullCategoryName() + " > " + category.getName();
            }
        }

        category.setFullCategoryName(fullName);

        categoryDao.insert(category);
    }

    public void updateCategory(Category category) {
        if (category.getParentCategoryId() == null) {
            category.setParentCategoryId(0);
        }

        // fullCategoryName 갱신
        int parentId = category.getParentCategoryId();
        String fullName = category.getName();

        if (parentId != 0) {
            Category parent = getCategoryById(parentId);
            if (parent != null) {
                fullName = parent.getFullCategoryName() + " > " + category.getName();
            }
        }

        category.setFullCategoryName(fullName);
        categoryDao.update(category);

        // 자식들도 fullName 갱신
        updateChildFullNames(category);
    }



    public boolean deleteCategory(int categoryId) {
        // 자식 존재 여부 확인
        List<Category> children = categoryDao.findChildren(categoryId);
        if (children != null && !children.isEmpty()) {
            return false; // 삭제 불가
        }

        return categoryDao.delete(categoryId);
    }


    public Category getCategoryById(int id) {
        return categoryDao.findById(id);
    }

    public int getCategoryLevel(int categoryId) {
        if (categoryId == 0) return 0;
        Category parent = getCategoryById(categoryId);
        return parent != null ? parent.getLevel() : 0;
    }

    public boolean hasChildCategories(int categoryId) {
        return !categoryDao.findChildren(categoryId).isEmpty();
    }

    private void updateChildFullNames(Category parent) {
        List<Category> children = categoryDao.findChildren(parent.getCategoryId());

        for (Category child : children) {
            // 자식의 fullCategoryName 갱신
            String newFullName = parent.getFullCategoryName() + " > " + child.getName();
            child.setFullCategoryName(newFullName);

            // 부모 레벨 + 1로 설정
            child.setLevel(parent.getLevel() + 1);

            categoryDao.update(child); // DB 반영

            // 재귀 호출로 하위도 계속 갱신
            updateChildFullNames(child);
        }
    }
}
