package com.eric.cakemall.dto;

public class QaImgDTO {

    private Integer qaNo;
    private String qaImgUrl;

    public QaImgDTO() {}

    public QaImgDTO(Integer qaNo, String qaImgUrl) {
        this.qaNo = qaNo;
        this.qaImgUrl = qaImgUrl;
    }

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
