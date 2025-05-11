package shoppingMall.domain;

import java.time.LocalDateTime;

public class OrderItem {

    private String idOrderItem;              // 주문 품목 ID
    private String idOrder;                  // 주문 ID
    private int cnOrderItem;                 // 주문 품목 순번
    private String noProduct;                // 상품 코드
    private String noUser;                   // 사용자 식별번호
    private int qtUnitPrice;                 // 주문 품목 단가
    private int qtOrderItem;                 // 주문 품목 수량
    private int qtOrderItemAmount;           // 주문 품목 금액
    private int qtOrderItemDeliveryFee;      // 주문 품목 배송 금액
    private String stPayment;                // 결제 상태
    private String noRegister;               // 최초 등록자 ID
    private LocalDateTime daFirstDate;       // 최초 등록 일시

    // Getters and Setters
    public String getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(String idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public int getCnOrderItem() {
        return cnOrderItem;
    }

    public void setCnOrderItem(int cnOrderItem) {
        this.cnOrderItem = cnOrderItem;
    }

    public String getNoProduct() {
        return noProduct;
    }

    public void setNoProduct(String noProduct) {
        this.noProduct = noProduct;
    }

    public String getNoUser() {
        return noUser;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public int getQtUnitPrice() {
        return qtUnitPrice;
    }

    public void setQtUnitPrice(int qtUnitPrice) {
        this.qtUnitPrice = qtUnitPrice;
    }

    public int getQtOrderItem() {
        return qtOrderItem;
    }

    public void setQtOrderItem(int qtOrderItem) {
        this.qtOrderItem = qtOrderItem;
    }

    public int getQtOrderItemAmount() {
        return qtOrderItemAmount;
    }

    public void setQtOrderItemAmount(int qtOrderItemAmount) {
        this.qtOrderItemAmount = qtOrderItemAmount;
    }

    public int getQtOrderItemDeliveryFee() {
        return qtOrderItemDeliveryFee;
    }

    public void setQtOrderItemDeliveryFee(int qtOrderItemDeliveryFee) {
        this.qtOrderItemDeliveryFee = qtOrderItemDeliveryFee;
    }

    public String getStPayment() {
        return stPayment;
    }

    public void setStPayment(String stPayment) {
        this.stPayment = stPayment;
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
