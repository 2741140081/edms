package com.marks.edms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MallUser {

    private Long userId;

    private String nickName;

    private String loginName;

    private String passwordMd5;

    private String introduceSign;

    private String address;

    private Byte isDeleted;

    private Byte lockedFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String userEmail;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date passwordUpdateTime;

    private String oldPassword1;

    private String oldPassword2;

    private String oldPassword3;

    private char passwordStatus;

    public MallUser() {
    }

    public MallUser(Long userId, String nickName, String loginName, String passwordMd5, String introduceSign, String address, Byte isDeleted, Byte lockedFlag, Date createTime, String userEmail, Date passwordUpdateTime, String oldPassword1, String oldPassword2, String oldPassword3, char passwordStatus) {
        this.userId = userId;
        this.nickName = nickName;
        this.loginName = loginName;
        this.passwordMd5 = passwordMd5;
        this.introduceSign = introduceSign;
        this.address = address;
        this.isDeleted = isDeleted;
        this.lockedFlag = lockedFlag;
        this.createTime = createTime;
        this.userEmail = userEmail;
        this.passwordUpdateTime = passwordUpdateTime;
        this.oldPassword1 = oldPassword1;
        this.oldPassword2 = oldPassword2;
        this.oldPassword3 = oldPassword3;
        this.passwordStatus = passwordStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }

    public String getIntroduceSign() {
        return introduceSign;
    }

    public void setIntroduceSign(String introduceSign) {
        this.introduceSign = introduceSign;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Byte getLockedFlag() {
        return lockedFlag;
    }

    public void setLockedFlag(Byte lockedFlag) {
        this.lockedFlag = lockedFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getPasswordUpdateTime() {
        return passwordUpdateTime;
    }

    public void setPasswordUpdateTime(Date passwordUpdateTime) {
        this.passwordUpdateTime = passwordUpdateTime;
    }

    public String getOldPassword1() {
        return oldPassword1;
    }

    public void setOldPassword1(String oldPassword1) {
        this.oldPassword1 = oldPassword1;
    }

    public String getOldPassword2() {
        return oldPassword2;
    }

    public void setOldPassword2(String oldPassword2) {
        this.oldPassword2 = oldPassword2;
    }

    public String getOldPassword3() {
        return oldPassword3;
    }

    public void setOldPassword3(String oldPassword3) {
        this.oldPassword3 = oldPassword3;
    }

    public char getPasswordStatus() {
        return passwordStatus;
    }

    public void setPasswordStatus(char passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    @Override
    public String toString() {
        return "MallUser{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", passwordMd5='" + passwordMd5 + '\'' +
                ", introduceSign='" + introduceSign + '\'' +
                ", address='" + address + '\'' +
                ", isDeleted=" + isDeleted +
                ", lockedFlag=" + lockedFlag +
                ", createTime=" + createTime +
                ", userEmail='" + userEmail + '\'' +
                ", passwordUpdateTime=" + passwordUpdateTime +
                ", oldPassword1='" + oldPassword1 + '\'' +
                ", oldPassword2='" + oldPassword2 + '\'' +
                ", oldPassword3='" + oldPassword3 + '\'' +
                ", passwordStatus=" + passwordStatus +
                '}';
    }
}
