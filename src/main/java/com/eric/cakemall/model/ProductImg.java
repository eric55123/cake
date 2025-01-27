package com.eric.cakemall.model;


import jakarta.persistence.*;

@Entity
@Table(name = "product_img")
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_no")
    private Integer productImgNo;

    @Column(name = "product_no")
    private Integer productNo;

    @Column(name = "product_img_url")
    private  String productImgUrl;

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