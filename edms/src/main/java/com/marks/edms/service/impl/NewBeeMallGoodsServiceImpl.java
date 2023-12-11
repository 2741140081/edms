package com.marks.edms.service.impl;

import com.marks.edms.common.NewBeeMallCategoryLevelEnum;
import com.marks.edms.controller.common.ServiceResultEnum;
import com.marks.edms.controller.vo.NewBeeMallSearchGoodsVO;
import com.marks.edms.dao.GoodsCategoryMapper;
import com.marks.edms.dao.NewBeeMallGoodsMapper;
import com.marks.edms.entity.GoodsCategory;
import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.service.NewBeeMallGoodsService;
import com.marks.edms.util.BeanUtil;
import com.marks.edms.util.NewBeeMallUtils;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class NewBeeMallGoodsServiceImpl implements NewBeeMallGoodsService {

    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @Override
    public String saveNewBeeMallGoods(NewBeeMallGoods goods) {
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(goods.getGoodsCategoryId());
        // 分类不存在或者不是三级分类，则该参数字段异常
        if (goodsCategory == null || goodsCategory.getCategoryLevel().intValue() != NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ServiceResultEnum.GOODS_CATEGORY_ERROR.getResult();
        }
        if (goodsMapper.selectByCategoryIdAndName(goods.getGoodsName(), goods.getGoodsCategoryId()) != null) {
            return ServiceResultEnum.SAME_GOODS_EXIST.getResult();
        }
        goods.setGoodsName(NewBeeMallUtils.cleanString(goods.getGoodsName()));
        goods.setGoodsIntro(NewBeeMallUtils.cleanString(goods.getGoodsIntro()));
        goods.setTag(NewBeeMallUtils.cleanString(goods.getTag()));
        if (goodsMapper.insertSelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    /**
     * 获取商品详情
     * @param goodsId
     * @return
     */
    @Override
    public NewBeeMallGoods getNewBeeMallGoodsById(Long goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    /**
     * 修改商品信息
     * @param goods
     * @return
     */
    @Override
    public String updateNewBeeMallGoods(NewBeeMallGoods goods) {
        NewBeeMallGoods tempGoods = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (tempGoods == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        /**
         * 如果当前goods存在记录在info表，则update
         * 如果当前用户不是admin，即update_user != 0
         * 则是在什么地方获取当前登录user的id信息，并且将信息保存在goods中
         * 如果说ServiceImpl专注于事务处理，那么应该在前端将数据保存在goods中，即前端返回的数据包含了用户修改的信息。
         */
        goods.setUpdateTime(new Date());
        if (goodsMapper.updateByPrimaryKeySelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    /**
     * 后台分页
     * @param pageQueryUtil
     * @return
     */
    @Override
    public PageResult getNewBeeMallGoodsPage(PageQueryUtil pageQueryUtil) {
        List<NewBeeMallGoods> newBeeMallGoodsList = goodsMapper.findNewBeeMallGoodsList(pageQueryUtil);
        int totalNewBeeMallGoods = goodsMapper.getTotalNewBeeMallGoods(pageQueryUtil);
        PageResult pageResult = new PageResult(totalNewBeeMallGoods, pageQueryUtil.getLimit(), pageQueryUtil.getPage(), newBeeMallGoodsList);
        return pageResult;
    }

    /**
     * 批量修改销售状态(上架下架)
     * @param goodIds
     * @param sellStatus
     * @return
     */
    @Override
    public Boolean batchUpdateSellStatus(Long[] goodIds, int sellStatus) {
        if (goodIds.length < 1 || Objects.isNull(sellStatus)) {
            return false;
        }
        return goodsMapper.batchUpdateSellStatus(goodIds, sellStatus) > 0;
    }

    /**
     * 商品搜索
     *
     * @param pageQueryUtil
     * @return
     */
    @Override
    public PageResult searchNewBeeMallGoods(PageQueryUtil pageQueryUtil) {
        int totalNewBeeMallGoodsBySearch = goodsMapper.getTotalNewBeeMallGoodsBySearch(pageQueryUtil);
        List<NewBeeMallGoods> newBeeMallGoodsListBySearch = goodsMapper.findNewBeeMallGoodsListBySearch(pageQueryUtil);

        List<NewBeeMallSearchGoodsVO> newBeeMallSearchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(newBeeMallGoodsListBySearch)) {
            newBeeMallSearchGoodsVOS = BeanUtil.copyList(newBeeMallGoodsListBySearch, NewBeeMallSearchGoodsVO.class);
            for (NewBeeMallSearchGoodsVO newBeeMallSearchGoodsVO : newBeeMallSearchGoodsVOS) {
                //解决字符串文字过长问题
                String goodsName = newBeeMallSearchGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallSearchGoodsVO.getGoodsIntro();
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    newBeeMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 28) {
                    goodsIntro = goodsIntro.substring(0, 28) + "...";
                    newBeeMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult result = new PageResult(totalNewBeeMallGoodsBySearch, pageQueryUtil.getLimit(), pageQueryUtil.getPage(), newBeeMallGoodsListBySearch);
        return result;
    }

    /**
     * 批量新增商品数据
     *
     * @param newBeeMallGoodsList
     * @return
     */
    @Override
    public void batchSaveNewBeeMallGoods(List<NewBeeMallGoods> newBeeMallGoodsList) {

    }


}
