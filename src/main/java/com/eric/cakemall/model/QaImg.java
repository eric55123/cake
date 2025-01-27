package com.eric.cakemall.model;

import jakarta.persistence.*;

@Entity
@Table(name ="qaImg")
public class QaImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qa_no")
    private Integer qaNo;

    @Column(name = "qa_img_url")
    private Integer qaImgUrl;

    public Integer getQaNo() {
        return qaNo;
    }

    public void setQaNo(Integer qaNo) {
        this.qaNo = qaNo;
    }

    public Integer getQaImgUrl() {
        return qaImgUrl;
    }

    public void setQaImgUrl(Integer qaImgUrl) {
        this.qaImgUrl = qaImgUrl;
    }
}