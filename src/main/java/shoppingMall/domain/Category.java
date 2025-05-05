package shoppingMall.domain;

import java.time.LocalDateTime;

public class Category {
    private int categoryId;               // nb_category
    private int parentCategoryId;        // nb_parent_category
    private String name;                 // nm_category
    private String fullCategoryName;     // nm_full_category
    private String description;          // nm_explain
    private int level;                   // cn_level
    private int order;                   // cn_order
    private boolean used;                // yn_use
    private boolean deleted;             // yn_delete
    private String createdBy;           // no_register
    private LocalDateTime createdAt;    // da_first_date

    public Category() {
    }

    public Category(int categoryId, int parentCategoryId, String name, String fullCategoryName, String description, int level, int order, boolean used, boolean deleted, String createdBy, LocalDateTime createdAt) {
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

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
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
