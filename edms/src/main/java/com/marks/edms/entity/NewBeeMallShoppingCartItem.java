package com.marks.edms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NewBeeMallShoppingCartItem {
    //购物项主键Id
    private Long cartItemId;

    //用户ID
    private Long userId;

    //商品ID
    private Long goodsId;

    //商品数量
    private Integer goodsCount;

    //是否删除0-未删除，1-已删除
    private Byte isDeleted;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    //最近修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public NewBeeMallShoppingCartItem() {
    }

    public NewBeeMallShoppingCartItem(Long cartItemId, Long userId, Long goodsId, Integer goodsCount, Byte isDeleted, Date createTime, Date updateTime) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.goodsId = goodsId;
        this.goodsCount = goodsCount;
        this.isDeleted = isDeleted;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
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
