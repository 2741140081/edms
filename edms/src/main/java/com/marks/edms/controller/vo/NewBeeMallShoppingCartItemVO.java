package com.marks.edms.controller.vo;

import java.io.Serializable;

/**
 * 购物车页面购物项VO
 */
public class NewBeeMallShoppingCartItemVO implements Serializable {
    private Long cartItemId;

    private Long goodsId;

    private Integer goodsCount;

    private String goodsName;

    private String goodsCoverImg;

    private Integer sellingPrice;

    public NewBeeMallShoppingCartItemVO() {
    }

    public NewBeeMallShoppingCartItemVO(Long cartItemId, Long goodsId, Integer goodsCount, String goodsName, String goodsCoverImg, Integer sellingPrice) {
        this.cartItemId = cartItemId;
        this.goodsId = goodsId;
        this.goodsCount = goodsCount;
        this.goodsName = goodsName;
        this.goodsCoverImg = goodsCoverImg;
        this.sellingPrice = sellingPrice;
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(String goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
