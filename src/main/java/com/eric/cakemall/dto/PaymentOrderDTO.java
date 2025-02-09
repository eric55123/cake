package com.eric.cakemall.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentOrderDTO {

    private Integer paymentOrderNo;
    private String totalAmount;
    private String paymentStatus;
    private LocalDateTime paymentTime;
    private List<PaymentOrderItemDTO> productItems;  // 包含商品名稱的項目清單

    // Getter & Setter 方法
    public Integer getPaymentOrderNo() {
        return paymentOrderNo;
    }

    public void setPaymentOrderNo(Integer paymentOrderNo) {
        this.paymentOrderNo = paymentOrderNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public List<PaymentOrderItemDTO> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<PaymentOrderItemDTO> productItems) {
        this.productItems = productItems;
    }
}
