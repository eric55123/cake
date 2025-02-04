package com.eric.cakemall.dto;

import java.time.LocalDateTime;

public class BulletinDTO {

    private Integer bulletinNo;
    private String bulletinTitle;
    private String bulletinContent;
    private LocalDateTime bulletinUpdateTime;
    private Integer adminId;

    public Integer getBulletinNo() {
        return bulletinNo;
    }

    public void setBulletinNo(Integer bulletinNo) {
        this.bulletinNo = bulletinNo;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public LocalDateTime getBulletinUpdateTime() {
        return bulletinUpdateTime;
    }

    public void setBulletinUpdateTime(LocalDateTime bulletinUpdateTime) {
        this.bulletinUpdateTime = bulletinUpdateTime;
    }

    public String getBulletinContent() {
        return bulletinContent;
    }

    public void setBulletinContent(String bulletinContent) {
        this.bulletinContent = bulletinContent;
    }

    public String getBulletinTitle() {
        return bulletinTitle;
    }

    public void setBulletinTitle(String bulletinTitle) {
        this.bulletinTitle = bulletinTitle;
    }
}