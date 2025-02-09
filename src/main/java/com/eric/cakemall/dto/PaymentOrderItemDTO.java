package com.eric.cakemall.dto;

public class PaymentOrderItemDTO {

    private Integer productNo;      // 商品編號
    private String productName;     // 商品名稱
    private Integer quantity;       // 商品數量

    // Getter & Setter 方法
    public Integer getProductNo() {
        return productNo;
    }

    public void setProductNo(Integer productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
