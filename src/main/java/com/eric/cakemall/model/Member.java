package com.eric.cakemall.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Integer memberNo; // 會員編號

    @Column(name = "member_account", nullable = false, unique = true, length = 20)
    private String memberAccount; // 帳號

    @Column(name = "member_email", nullable = false, length = 50)
    private String memberEmail; // 電子郵件

    @Column(name = "member_pwd", nullable = false, length = 64)
    private String memberPwd; // 密碼

    @Column(name = "member_name", nullable = false, length = 50)
    private String memberName; // 姓名

    @Column(name = "birthday")
    private LocalDate birthday; // 生日

    @Column(name = "member_phone", length = 15)
    private String memberPhone; // 手機號碼

    @Column(name = "address", length = 200)
    private String address; // 地址

    @Column(name = "member_status", nullable = false, length = 1)
    private String memberStatus; // 會員狀態 (0 = 停用, 1 = 啟用)

    @Column(name = "member_created", nullable = false)
    private LocalDateTime memberCreated; // 創建時間

    @Column(name = "member_updated")
    private LocalDateTime memberUpdated; // 更新時間

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public LocalDateTime getMemberCreated() {
        return memberCreated;
    }

    public void setMemberCreated(LocalDateTime memberCreated) {
        this.memberCreated = memberCreated;
    }

    public LocalDateTime getMemberUpdated() {
        return memberUpdated;
    }

    public void setMemberUpdated(LocalDateTime memberUpdated) {
        this.memberUpdated = memberUpdated;
    }
}