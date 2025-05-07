package shoppingMall.domain;

import shoppingMall.domain.enums.YnFlag;

import java.time.LocalDateTime;

public class Category {
    private int categoryId;               // nb_category
    private Integer parentCategoryId;        // nb_parent_category
    private String name;                 // nm_category
    private String fullCategoryName;     // nm_full_category
    private String description;          // nm_explain
    private Integer level;                   // cn_level
    private int order;                   // cn_order
    private YnFlag used;    // yn_use
    private YnFlag deleted; // yn_delete
    private String createdBy;           // no_register
    private LocalDateTime createdAt;    // da_first_date

    public Category() {
    }

    public Category(int categoryId, Integer parentCategoryId, String name, String fullCategoryName, String description, Integer level, int order, YnFlag used, YnFlag deleted, String createdBy, LocalDateTime createdAt) {
        this.categoryId = categoryId;
        this.parentCategoryId = parentCategoryId;
        this.name = name;
        this.fullCategoryName = fullCategoryName;
        this.description = description;
        this.level = level;
        this.order = order;
        this.used = used;
        this.deleted = deleted;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullCategoryName() {
        return fullCategoryName;
    }

    public void setFullCategoryName(String fullCategoryName) {
        this.fullCategoryName = fullCategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
