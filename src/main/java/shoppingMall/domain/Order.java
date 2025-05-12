package shoppingMall.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Order {
    private String idOrder;
    private String noUser;
    private int qtOrderAmount;
    private int qtDeliMoney;
    private int qtDeliPeriod;
    private String nmOrderPerson;
    private String nmReceiver;
    private String noDeliveryZipno;
    private String nmDeliveryAddress;
    private String nmReceiverTelno;
    private String nmDeliverySpace;
    private String cdOrderType;
    private LocalDateTime daOrder;
    private LocalDateTime daFirstDate;
    private String stOrder;
    private String stPayment;
    private String noRegister;

    // 추가: JSTL 호환용 java.util.Date
    private Date convertedDate;

    // Getters and Setters
    public String getIdOrder() { return idOrder; }
    public void setIdOrder(String idOrder) { this.idOrder = idOrder; }

    public String getNoUser() { return noUser; }
    public void setNoUser(String noUser) { this.noUser = noUser; }

    public int getQtOrderAmount() { return qtOrderAmount; }
    public void setQtOrderAmount(int qtOrderAmount) { this.qtOrderAmount = qtOrderAmount; }

    public int getQtDeliMoney() { return qtDeliMoney; }
    public void setQtDeliMoney(int qtDeliMoney) { this.qtDeliMoney = qtDeliMoney; }

    public int getQtDeliPeriod() { return qtDeliPeriod; }
    public void setQtDeliPeriod(int qtDeliPeriod) { this.qtDeliPeriod = qtDeliPeriod; }

    public String getNmOrderPerson() { return nmOrderPerson; }
    public void setNmOrderPerson(String nmOrderPerson) { this.nmOrderPerson = nmOrderPerson; }

    public String getNmReceiver() { return nmReceiver; }
    public void setNmReceiver(String nmReceiver) { this.nmReceiver = nmReceiver; }

    public String getNoDeliveryZipno() { return noDeliveryZipno; }
    public void setNoDeliveryZipno(String noDeliveryZipno) { this.noDeliveryZipno = noDeliveryZipno; }

    public String getNmDeliveryAddress() { return nmDeliveryAddress; }
    public void setNmDeliveryAddress(String nmDeliveryAddress) { this.nmDeliveryAddress = nmDeliveryAddress; }

    public String getNmReceiverTelno() { return nmReceiverTelno; }
    public void setNmReceiverTelno(String nmReceiverTelno) { this.nmReceiverTelno = nmReceiverTelno; }

    public String getNmDeliverySpace() { return nmDeliverySpace; }
    public void setNmDeliverySpace(String nmDeliverySpace) { this.nmDeliverySpace = nmDeliverySpace; }

    public String getCdOrderType() { return cdOrderType; }
    public void setCdOrderType(String cdOrderType) { this.cdOrderType = cdOrderType; }

    public LocalDateTime getDaOrder() { return daOrder; }
    public void setDaOrder(LocalDateTime daOrder) { this.daOrder = daOrder; }

    public LocalDateTime getDaFirstDate() { return daFirstDate; }
    public void setDaFirstDate(LocalDateTime daFirstDate) { this.daFirstDate = daFirstDate; }

    public String getStOrder() { return stOrder; }
    public void setStOrder(String stOrder) { this.stOrder = stOrder; }

    public String getStPayment() { return stPayment; }
    public void setStPayment(String stPayment) { this.stPayment = stPayment; }

    public String getNoRegister() { return noRegister; }
    public void setNoRegister(String noRegister) { this.noRegister = noRegister; }

    public Date getConvertedDate() { return convertedDate; }
    public void setConvertedDate(Date convertedDate) { this.convertedDate = convertedDate; }
}
