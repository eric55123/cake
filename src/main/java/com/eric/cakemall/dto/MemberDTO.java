package com.eric.cakemall.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberDTO {
    private Integer memberNo;
    private String memberAccount;
    private String memberEmail;
    private String memberPwd;
    private String memberName;
    private LocalDate birthday;
    private String memberPhone;
    private String address;
    private String memberStatus;
    private LocalDateTime memberCreated;
    private LocalDateTime memberUpdated;

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