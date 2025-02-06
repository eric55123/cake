package com.eric.cakemall.dto;

public class ProductImgDTO {

    private Integer productImgNo;
    private Integer productNo;
    private String productImgUrl;

    // 預設建構子
    public ProductImgDTO() {}

    // 三個參數的建構子
    public ProductImgDTO(Integer productImgNo, Integer productNo, String productImgUrl) {
        this.productImgNo = productImgNo;
        this.productNo = productNo;
        this.productImgUrl = productImgUrl;
    }

    // Getter & Setter
    public Integer getProductImgNo() {
        return productImgNo;
    }

    public void setProductImgNo(Integer productImgNo) {
        this.productImgNo = productImgNo;
    }

    public Integer getProductNo() {
        return productNo;
    }

    public void setProductNo(Integer productNo) {
        this.productNo = productNo;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }
}
