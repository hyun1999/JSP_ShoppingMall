package shoppingMall.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class OrderItem {
    private String idOrderItem;
    private String idOrder;
    private int cnOrderItem;
    private String noProduct;
    private String noUser;
    private int qtUnitPrice;
    private int qtOrderItem;
    private int qtOrderItemAmount;
    private int qtOrderItemDeliveryFee;
    private String stPayment;
    private String noRegister;
    private LocalDateTime daFirstDate;

    // 추가: JSTL 호환용 java.util.Date
    private Date convertedDate;

    // Getters and Setters
    public String getIdOrderItem() { return idOrderItem; }
    public void setIdOrderItem(String idOrderItem) { this.idOrderItem = idOrderItem; }

    public String getIdOrder() { return idOrder; }
    public void setIdOrder(String idOrder) { this.idOrder = idOrder; }

    public int getCnOrderItem() { return cnOrderItem; }
    public void setCnOrderItem(int cnOrderItem) { this.cnOrderItem = cnOrderItem; }

    public String getNoProduct() { return noProduct; }
    public void setNoProduct(String noProduct) { this.noProduct = noProduct; }

    public String getNoUser() { return noUser; }
    public void setNoUser(String noUser) { this.noUser = noUser; }

    public int getQtUnitPrice() { return qtUnitPrice; }
    public void setQtUnitPrice(int qtUnitPrice) { this.qtUnitPrice = qtUnitPrice; }

    public int getQtOrderItem() { return qtOrderItem; }
    public void setQtOrderItem(int qtOrderItem) { this.qtOrderItem = qtOrderItem; }

    public int getQtOrderItemAmount() { return qtOrderItemAmount; }
    public void setQtOrderItemAmount(int qtOrderItemAmount) { this.qtOrderItemAmount = qtOrderItemAmount; }

    public int getQtOrderItemDeliveryFee() { return qtOrderItemDeliveryFee; }
    public void setQtOrderItemDeliveryFee(int qtOrderItemDeliveryFee) { this.qtOrderItemDeliveryFee = qtOrderItemDeliveryFee; }

    public String getStPayment() { return stPayment; }
    public void setStPayment(String stPayment) { this.stPayment = stPayment; }

    public String getNoRegister() { return noRegister; }
    public void setNoRegister(String noRegister) { this.noRegister = noRegister; }

    public LocalDateTime getDaFirstDate() { return daFirstDate; }
    public void setDaFirstDate(LocalDateTime daFirstDate) { this.daFirstDate = daFirstDate; }

    public Date getConvertedDate() { return convertedDate; }
    public void setConvertedDate(Date convertedDate) { this.convertedDate = convertedDate; }
}
