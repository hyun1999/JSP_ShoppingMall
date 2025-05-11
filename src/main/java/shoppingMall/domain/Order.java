package shoppingMall.domain;

import java.time.LocalDateTime;

public class Order {

    private String idOrder;                  // 주문 ID
    private String noUser;                   // 사용자 식별번호
    private int qtOrderAmount;              // 주문 금액
    private int qtDeliMoney;                // 배송 금액
    private int qtDeliPeriod;               // 배송 기간
    private String nmOrderPerson;           // 주문자 명
    private String nmReceiver;              // 인수자 명
    private String noDeliveryZipno;         // 배송 우편번호
    private String nmDeliveryAddress;       // 배송 주소
    private String nmReceiverTelno;         // 인수자 연락처
    private String nmDeliverySpace;         // 배송 장소
    private String cdOrderType;             // 주문 구분 코드
    private LocalDateTime daOrder;          // 주문 일시
    private String stOrder;                 // 주문 상태
    private String stPayment;               // 결제 상태
    private String noRegister;              // 최초 등록자 ID
    private LocalDateTime daFirstDate;      // 최초 등록 일시

    // Getters and Setters
    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getNoUser() {
        return noUser;
    }

    public void setNoUser(String noUser) {
        this.noUser = noUser;
    }

    public int getQtOrderAmount() {
        return qtOrderAmount;
    }

    public void setQtOrderAmount(int qtOrderAmount) {
        this.qtOrderAmount = qtOrderAmount;
    }

    public int getQtDeliMoney() {
        return qtDeliMoney;
    }

    public void setQtDeliMoney(int qtDeliMoney) {
        this.qtDeliMoney = qtDeliMoney;
    }

    public int getQtDeliPeriod() {
        return qtDeliPeriod;
    }

    public void setQtDeliPeriod(int qtDeliPeriod) {
        this.qtDeliPeriod = qtDeliPeriod;
    }

    public String getNmOrderPerson() {
        return nmOrderPerson;
    }

    public void setNmOrderPerson(String nmOrderPerson) {
        this.nmOrderPerson = nmOrderPerson;
    }

    public String getNmReceiver() {
        return nmReceiver;
    }

    public void setNmReceiver(String nmReceiver) {
        this.nmReceiver = nmReceiver;
    }

    public String getNoDeliveryZipno() {
        return noDeliveryZipno;
    }

    public void setNoDeliveryZipno(String noDeliveryZipno) {
        this.noDeliveryZipno = noDeliveryZipno;
    }

    public String getNmDeliveryAddress() {
        return nmDeliveryAddress;
    }

    public void setNmDeliveryAddress(String nmDeliveryAddress) {
        this.nmDeliveryAddress = nmDeliveryAddress;
    }

    public String getNmReceiverTelno() {
        return nmReceiverTelno;
    }

    public void setNmReceiverTelno(String nmReceiverTelno) {
        this.nmReceiverTelno = nmReceiverTelno;
    }

    public String getNmDeliverySpace() {
        return nmDeliverySpace;
    }

    public void setNmDeliverySpace(String nmDeliverySpace) {
        this.nmDeliverySpace = nmDeliverySpace;
    }

    public String getCdOrderType() {
        return cdOrderType;
    }

    public void setCdOrderType(String cdOrderType) {
        this.cdOrderType = cdOrderType;
    }

    public LocalDateTime getDaOrder() {
        return daOrder;
    }

    public void setDaOrder(LocalDateTime daOrder) {
        this.daOrder = daOrder;
    }

    public String getStOrder() {
        return stOrder;
    }

    public void setStOrder(String stOrder) {
        this.stOrder = stOrder;
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
