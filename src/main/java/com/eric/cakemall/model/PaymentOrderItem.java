package com.eric.cakemall.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_order_item")
public class PaymentOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_order_item_no")
    private Integer orderItemNo;

    @ManyToOne
    @JoinColumn(name = "payment_order_no", nullable = false)
    private PaymentOrder paymentOrder;

    @ManyToOne
    @JoinColumn(name = "product_no", nullable = false)
    private Product product;

    @Column(name = "unit_price", nullable = false)
    private String unitPrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    // Getter & Setter
    public Integer getOrderItemNo() {
        return orderItemNo;
    }

    public void setOrderItemNo(Integer orderItemNo) {
        this.orderItemNo = orderItemNo;
    }

    public PaymentOrder getPaymentOrder() {
        return paymentOrder;
    }

    public void setPaymentOrder(PaymentOrder paymentOrder) {
        this.paymentOrder = paymentOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
