package shoppingMall.domain;

import java.time.LocalDateTime;

public class Product {
    private String noProduct;            // 상품 코드 (PK)
    private String nmProduct;            // 상품 명
    private String nmDetailExplain;      // 상세 설명
    private String idFile;               // 컨텐츠 식별 ID
    private String dtStartDate;          // 판매 시작 일자 (YYYYMMDD)
    private String dtEndDate;            // 판매 종료 일자 (YYYYMMDD)
    private Integer qtCustomer;          // 소비자 가격
    private Integer qtSalePrice;         // 판매 가격
    private Integer qtStock;             // 재고 수량
    private Integer qtDeliveryFee;       // 배송비 금액
    private String noRegister;           // 최초 등록자 ID
    private LocalDateTime daFirstDate;            // 최초 등록 일시

    public Product() {
    }

    public Product(String noProduct, String nmProduct, String nmDetailExplain, String idFile, String dtStartDate, String dtEndDate, Integer qtCustomer, Integer qtSalePrice, Integer qtStock, Integer qtDeliveryFee, String noRegister, LocalDateTime daFirstDate) {
        this.noProduct = noProduct;
        this.nmProduct = nmProduct;
        this.nmDetailExplain = nmDetailExplain;
        this.idFile = idFile;
        this.dtStartDate = dtStartDate;
        this.dtEndDate = dtEndDate;
        this.qtCustomer = qtCustomer;
        this.qtSalePrice = qtSalePrice;
        this.qtStock = qtStock;
        this.qtDeliveryFee = qtDeliveryFee;
        this.noRegister = noRegister;
        this.daFirstDate = daFirstDate;
    }

    public String getNoProduct() {
        return noProduct;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public String getNmProduct() {
        return nmProduct;
    }

    public void setNmProduct(String nmProduct) {
        this.nmProduct = nmProduct;
    }

    public String getNmDetailExplain() {
        return nmDetailExplain;
    }

    public void setNmDetailExplain(String nmDetailExplain) {
        this.nmDetailExplain = nmDetailExplain;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getDtStartDate() {
        return dtStartDate;
    }

    public void setDtStartDate(String dtStartDate) {
        this.dtStartDate = dtStartDate;
    }

    public String getDtEndDate() {
        return dtEndDate;
    }

    public void setDtEndDate(String dtEndDate) {
        this.dtEndDate = dtEndDate;
    }

    public Integer getQtCustomer() {
        return qtCustomer;
    }

    public void setQtCustomer(Integer qtCustomer) {
        this.qtCustomer = qtCustomer;
    }

    public Integer getQtSalePrice() {
        return qtSalePrice;
    }

    public void setQtSalePrice(Integer qtSalePrice) {
        this.qtSalePrice = qtSalePrice;
    }

    public Integer getQtStock() {
        return qtStock;
    }

    public void setQtStock(Integer qtStock) {
        this.qtStock = qtStock;
    }

    public Integer getQtDeliveryFee() {
        return qtDeliveryFee;
    }

    public void setQtDeliveryFee(Integer qtDeliveryFee) {
        this.qtDeliveryFee = qtDeliveryFee;
    }

    public String getNoRegister() {
        return noRegister;
    }

    public void setNoRegister(String noRegister) {
        this.noRegister = noRegister;
    }

    public LocalDateTime getDaFirstDate() {
        return daFirstDate;
    }

    public void setDaFirstDate(LocalDateTime daFirstDate) {
        this.daFirstDate = daFirstDate;
    }
}
