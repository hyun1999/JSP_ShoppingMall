package shoppingMall.service;

import shoppingMall.dao.CategoryProductMappingDao;
import shoppingMall.domain.CategoryProductMapping;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class CategoryProductMappingService {
    private final CategoryProductMappingDao categoryProductMappingDao = new CategoryProductMappingDao();

    public void createMapping(int categoryId, String productId, String register, Connection conn) throws SQLException {
        CategoryProductMapping mapping = new CategoryProductMapping(categoryId, productId, 1, register, new Date());
        categoryProductMappingDao.insert(mapping, conn);
    }

    public void deleteMapping(int categoryId, String productId) throws SQLException {
        categoryProductMappingDao.delete(categoryId, productId);
    }
}
