package com.eric.cakemall.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment_order")
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_order_no")
    private Integer paymentOrderNo;

    @OneToMany(mappedBy = "paymentOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentOrderItem> orderItems = new ArrayList<>();

    @Column(name = "total_amount", nullable = false)
    private String totalAmount;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "payment_time")
    private LocalDateTime paymentTime;

    @Column(name = "trade_no", nullable = false, unique = true)
    private String tradeNo;

    public void addOrderItem(PaymentOrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setPaymentOrder(this);
    }

    public Integer getPaymentOrderNo() {
        return paymentOrderNo;
    }

    public void setPaymentOrderNo(Integer paymentOrderNo) {
        this.paymentOrderNo = paymentOrderNo;
    }

    public List<PaymentOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<PaymentOrderItem> orderItems) {
        this.orderItems = orderItems;
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

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
