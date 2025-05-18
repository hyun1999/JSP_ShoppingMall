package shoppingMall.dto;

import jakarta.servlet.http.HttpServletRequest;

public class ProductDto {
    private String noProduct;
    private String nmProduct;
    private String nmDetailExplain;
    private String dtStartDate;
    private String dtEndDate;
    private int qtCustomerPrice;
    private int qtSalePrice;
    private int qtStock;
    private int qtDeliveryFee;
    private String categoryId;

    public ProductDto(String noProduct, String nmProduct, String nmDetailExplain, String dtStartDate,
                      String dtEndDate, int qtCustomerPrice, int qtSalePrice, int qtStock,
                      int qtDeliveryFee, String categoryId) {
        this.noProduct = noProduct;
        this.nmProduct = nmProduct;
        this.nmDetailExplain = nmDetailExplain;
        this.dtStartDate = dtStartDate;
        this.dtEndDate = dtEndDate;
        this.qtCustomerPrice = qtCustomerPrice;
        this.qtSalePrice = qtSalePrice;
        this.qtStock = qtStock;
        this.qtDeliveryFee = qtDeliveryFee;
        this.categoryId = categoryId;
    }

    public static ProductDto fromRequest(HttpServletRequest request) {
        return new ProductDto(
                request.getParameter("noProduct"),
                request.getParameter("nmProduct"),
                request.getParameter("nmDetailExplain"),
                request.getParameter("dtStartDate"),
                request.getParameter("dtEndDate"),
                Integer.parseInt(request.getParameter("qtCustomerPrice")),
                Integer.parseInt(request.getParameter("qtSalePrice")),
                Integer.parseInt(request.getParameter("qtStock")),
                Integer.parseInt(request.getParameter("qtDeliveryFee")),
                request.getParameter("categoryId")
        );
    }

    public ProductDto() {
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

    public int getQtCustomerPrice() {
        return qtCustomerPrice;
    }

    public void setQtCustomerPrice(int qtCustomerPrice) {
        this.qtCustomerPrice = qtCustomerPrice;
    }

    public int getQtSalePrice() {
        return qtSalePrice;
    }

    public void setQtSalePrice(int qtSalePrice) {
        this.qtSalePrice = qtSalePrice;
    }

    public int getQtStock() {
        return qtStock;
    }

    public void setQtStock(int qtStock) {
        this.qtStock = qtStock;
    }

    public int getQtDeliveryFee() {
        return qtDeliveryFee;
    }

    public void setQtDeliveryFee(int qtDeliveryFee) {
        this.qtDeliveryFee = qtDeliveryFee;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
