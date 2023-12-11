package com.marks.edms.service;

import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;

import java.util.List;


public interface NewBeeMallGoodsService {
    /**
     * 后台分页
     * @param pageQueryUtil
     * @return
     */
    PageResult getNewBeeMallGoodsPage(PageQueryUtil pageQueryUtil);

    /**
     * 添加商品
     * @param goods
     * @return
     */
    String saveNewBeeMallGoods(NewBeeMallGoods goods);

    /**
     * 获取商品详情
     * @param goodsId
     * @return
     */
    NewBeeMallGoods getNewBeeMallGoodsById(Long goodsId);

    /**
     * 修改商品信息
     * @param goods
     * @return
     */
    String updateNewBeeMallGoods(NewBeeMallGoods goods);

    /**
     * 批量修改销售状态(上架下架)
     * @param goodIds
     * @param sellStatus
     * @return
     */
    Boolean batchUpdateSellStatus(Long[] goodIds, int sellStatus);

    /**
     * 商品搜索
     * @param pageQueryUtil
     * @return
     */
     PageResult searchNewBeeMallGoods(PageQueryUtil pageQueryUtil);

    /**
     * 批量新增商品数据
     *
     * @param newBeeMallGoodsList
     * @return
     */
    void batchSaveNewBeeMallGoods(List<NewBeeMallGoods> newBeeMallGoodsList);
}
