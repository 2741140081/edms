package com.marks.edms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NewBeeMallOrder {
    //订单表主键id
    private Long orderId;

    //订单号
    private String orderNo;

    //用户主键id
    private Long userId;

    //订单总价
    private Integer totalPrice;

    //支付状态: '0'-未支付，'1'-支付成功，'-1'-支付失败
    private Byte payStatus;

    //支付类型: '0'-无，1-支付宝支付，2-微信支付
    private Byte payType;

    //支付时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;

    //订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭
    private Byte orderStatus;

    //订单body
    private String extraInfo;

    //收货人姓名
    private String userName;

    //收货人手机号
    private String userPhone;

    //收货人地址
    private String userAddress;

    //是否删除:0-未删除，1-已删除
    private Byte isDeleted;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    //最新修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public NewBeeMallOrder() {
    }

    public NewBeeMallOrder(Long orderId, String orderNo, Long userId, Integer totalPrice, Byte payStatus, Byte payType, Date payTime, Byte orderStatus, String extraInfo, String userName, String userPhone, String userAddress, Byte isDeleted, Date createTime, Date updateTime) {
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.payStatus = payStatus;
        this.payType = payType;
        this.payTime = payTime;
        this.orderStatus = orderStatus;
        this.extraInfo = extraInfo;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.isDeleted = isDeleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
