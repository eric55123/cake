package com.eric.cakemall.model;

import jakarta.persistence.*;

@Entity
@Table(name = "qa_img")
public class QaImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qa_no")
    private Integer qaNo;

    @Column(name = "qa_img_url", nullable = false, length = 255)
    private String qaImgUrl;

    public Integer getQaNo() {
        return qaNo;
    }

    public void setQaNo(Integer qaNo) {
        this.qaNo = qaNo;
    }

    public String getQaImgUrl() {
        return qaImgUrl;
    }

    public void setQaImgUrl(String qaImgUrl) {
        this.qaImgUrl = qaImgUrl;
    }
}
