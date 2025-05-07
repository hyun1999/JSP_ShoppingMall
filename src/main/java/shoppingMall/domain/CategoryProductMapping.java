package shoppingMall.domain;


import java.util.Date;

public class CategoryProductMapping {

    private Integer nbCategory;      // 카테고리 식별번호 (PK, FK)
    private String noProduct;        // 상품 코드 (PK, FK)
    private Integer cnOrder;         // 순번
    private String noRegister;       // 최초 등록자 ID
    private Date daFirstDate;        // 최초 등록 일시

    public CategoryProductMapping() {}

    public CategoryProductMapping(Integer nbCategory, String noProduct, Integer cnOrder, String noRegister, Date daFirstDate) {
        this.nbCategory = nbCategory;
        this.noProduct = noProduct;
        this.cnOrder = cnOrder;
        this.noRegister = noRegister;
        this.daFirstDate = daFirstDate;
    }

    public Integer getNbCategory() {
        return nbCategory;
    }

    public void setNbCategory(Integer nbCategory) {
        this.nbCategory = nbCategory;
    }

    public String getNoProduct() {
        return noProduct;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public Integer getCnOrder() {
        return cnOrder;
    }

    public void setCnOrder(Integer cnOrder) {
        this.cnOrder = cnOrder;
    }

    public String getNoRegister() {
        return noRegister;
    }

    public void setNoRegister(String noRegister) {
        this.noRegister = noRegister;
    }

    public Date getDaFirstDate() {
        return daFirstDate;
    }

    public void setDaFirstDate(Date daFirstDate) {
        this.daFirstDate = daFirstDate;
    }


}
