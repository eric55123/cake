package com.eric.cakemall.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId; // 管理員編號

    @Column(name = "admin_account", nullable = false, unique = true, length = 50)
    private String adminAccount; // 帳號

    @Column(name = "admin_password", nullable = false, length = 64)
    private String adminPassword; // 密碼

    @Column(name = "admin_mail", length = 50)
    private String adminMail; // 電子郵件

    @Column(name = "admn_controller", nullable = false)
    private Integer admnController; // 是否有控制權限 (0 = 無控制權, 1 = 有控制權)

    @Column(name = "admin_status", nullable = false)
    private Integer adminStatus; // 狀態 (0 = 停用, 1 = 啟用)

    @Column(name = "admin_created", nullable = false)
    private LocalDateTime adminCreated; // 創建時間

    @Column(name = "admin_updated")
    private LocalDateTime adminUpdated; // 最後更新時間

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Integer getAdmnController() {
        return admnController;
    }

    public void setAdmnController(Integer admnController) {
        this.admnController = admnController;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }

    public Integer getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(Integer adminStatus) {
        this.adminStatus = adminStatus;
    }

    public LocalDateTime getAdminCreated() {
        return adminCreated;
    }

    public void setAdminCreated(LocalDateTime adminCreated) {
        this.adminCreated = adminCreated;
    }

    public LocalDateTime getAdminUpdated() {
        return adminUpdated;
    }

    public void setAdminUpdated(LocalDateTime adminUpdated) {
        this.adminUpdated = adminUpdated;
    }
}