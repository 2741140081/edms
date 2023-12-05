package com.marks.edms.controller.vo;

import java.io.Serializable;

/**
 * 搜索列表页商品VO
 */
public class NewBeeMallSearchGoodsVO implements Serializable {
    private Long goodsId;

    private String goodsName;

    private String goodsIntro;

    private String goodsCoverImg;

    private Integer sellingPrice;

    public NewBeeMallSearchGoodsVO() {
    }

    public NewBeeMallSearchGoodsVO(Long goodsId, String goodsName, String goodsIntro, String goodsCoverImg, Integer sellingPrice) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsIntro = goodsIntro;
        this.goodsCoverImg = goodsCoverImg;
        this.sellingPrice = sellingPrice;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsIntro() {
        return goodsIntro;
    }

    public void setGoodsIntro(String goodsIntro) {
        this.goodsIntro = goodsIntro;
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
