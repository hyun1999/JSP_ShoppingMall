package shoppingMall.dto;

import shoppingMall.domain.Category;
import shoppingMall.domain.enums.YnFlag;

import java.time.LocalDateTime;

public class CategoryDto {
    private Integer categoryId;
    private String name;
    private String description;
    private Integer parentCategoryId;
    private YnFlag used;
    private YnFlag deleted;

    public Category toEntity() {
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setName(name);
        category.setDescription(description);
        category.setParentCategoryId(parentCategoryId != null ? parentCategoryId : 0);
        category.setUsed(used != null ? used : YnFlag.YES);
        category.setDeleted(deleted != null ? deleted : YnFlag.NO);
        category.setCreatedAt(LocalDateTime.now());
        category.setCreatedBy("admin");
        return category;
    }

    public static CategoryDto fromEntity(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setParentCategoryId(category.getParentCategoryId());
        dto.setUsed(category.getUsed());
        dto.setDeleted(category.getDeleted());
        return dto;
    }

    public CategoryDto(Integer categoryId, String name, String description, Integer parentCategoryId, YnFlag used, YnFlag deleted) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.parentCategoryId = parentCategoryId;
        this.used = used;
        this.deleted = deleted;
    }

    public CategoryDto() {
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public YnFlag getUsed() {
        return used;
    }

    public void setUsed(YnFlag used) {
        this.used = used;
    }

    public YnFlag getDeleted() {
        return deleted;
    }

    public void setDeleted(YnFlag deleted) {
        this.deleted = deleted;
    }
}
