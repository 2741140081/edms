package com.marks.edms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NewBeeMallOrderItem {
    //订单关联购物项主键ID
    private Long orderItemId;

    //订单ID
    private Long orderId;

    //商品ID
    private Long goodsId;

    //下单时商品的名称_订单快照
    private String goodsName;

    //下单时商品的主图_订单快照
    private String goodsCoverImg;

    //下单时商品的价格_订单快照
    private Integer sellingCount;

    //数量_订单快照
    private Integer goodsCount;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public NewBeeMallOrderItem() {
    }

    public NewBeeMallOrderItem(Long orderItemId, Long orderId, Long goodsId, String goodsName, String goodsCoverImg, Integer sellingCount, Integer goodsCount, Date createTime) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsCoverImg = goodsCoverImg;
        this.sellingCount = sellingCount;
        this.goodsCount = goodsCount;
        this.createTime = createTime;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(String goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg;
    }

    public Integer getSellingCount() {
        return sellingCount;
    }

    public void setSellingCount(Integer sellingCount) {
        this.sellingCount = sellingCount;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
