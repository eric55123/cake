package com.eric.cakemall.model;


import jakarta.persistence.*;

@Entity
@Table(name = "product_img")
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_no")
    private Integer productImgNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_no", nullable = false)
    private Product product;

    @Column(name = "product_img_url")
    private  String productImgUrl;

    public Integer getProductImgNo() {
        return productImgNo;
    }

    public void setProductImgNo(Integer productImgNo) {
        this.productImgNo = productImgNo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductImgUrl() {
        return productImgUrl;
    }

    public void setProductImgUrl(String productImgUrl) {
        this.productImgUrl = productImgUrl;
    }
}