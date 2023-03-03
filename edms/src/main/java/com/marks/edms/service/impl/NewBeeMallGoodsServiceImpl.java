package com.marks.edms.service.impl;

import com.marks.edms.controller.common.ServiceResultEnum;
import com.marks.edms.dao.NewBeeMallGoodsMapper;
import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.service.NewBeeMallGoodsService;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class NewBeeMallGoodsServiceImpl implements NewBeeMallGoodsService {

    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;

    @Override
    public String saveNewBeeMallGoods(NewBeeMallGoods goods) {
        if (goodsMapper.insertSelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public NewBeeMallGoods getNewBeeMallGoodsById(Long goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

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


}
