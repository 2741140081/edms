package com.marks.edms.entity;

/**
 * 库存修改实体类
 */
public class StockNumDTO {
    private Long goodsId;

    private Integer goodsCount;

    public StockNumDTO() {
    }

    public StockNumDTO(Long goodsId, Integer goodsCount) {
        this.goodsId = goodsId;
        this.goodsCount = goodsCount;
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
}
