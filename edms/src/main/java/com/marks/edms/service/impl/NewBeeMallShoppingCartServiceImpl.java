package com.marks.edms.service.impl;

import com.marks.edms.common.Constants;
import com.marks.edms.common.ServiceResultEnum;
import com.marks.edms.controller.vo.NewBeeMallShoppingCartItemVO;
import com.marks.edms.dao.MallUserMapper;
import com.marks.edms.dao.NewBeeMallGoodsMapper;
import com.marks.edms.dao.NewBeeMallShoppingCartItemMapper;
import com.marks.edms.entity.MallUser;
import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.entity.NewBeeMallShoppingCartItem;
import com.marks.edms.service.NewBeeMallShoppingCartService;
import com.marks.edms.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NewBeeMallShoppingCartServiceImpl implements NewBeeMallShoppingCartService {

    @Autowired
    private NewBeeMallShoppingCartItemMapper shoppingCartItemMapper;

    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;

    @Autowired
    private MallUserMapper userMapper;

    /**
     * 保存商品至购物车
     *
     * @param newBeeMallShoppingCartItem
     * @return
     */
    @Override
    public String saveNewBeeMallCartItem(NewBeeMallShoppingCartItem newBeeMallShoppingCartItem) {
        /**
         * 判断添加到购物车中的商品是否存在
         * 用户Id是否存在
         * 库存是否足够
         */
        if (newBeeMallShoppingCartItem == null) {
            return ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult();
        }
        Long userId = newBeeMallShoppingCartItem.getUserId();
        Long goodsId = newBeeMallShoppingCartItem.getGoodsId();

        Integer goodsCount = newBeeMallShoppingCartItem.getGoodsCount();
        //超出最大数量
        if (goodsCount >= Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }

        int totalItem = shoppingCartItemMapper.selectCountByUserId(newBeeMallShoppingCartItem.getUserId()) + 1;
        //超出单个商品的最大数量
        if (newBeeMallShoppingCartItem.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }

        MallUser mallUser = userMapper.selectByPrimaryKey(userId);
        if (mallUser == null) {
            return ServiceResultEnum.MALL_USER_ID_DON_EXIST.getResult();
        }

        NewBeeMallGoods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if (goods == null) {
            return ServiceResultEnum.GOODS_NOT_EXIST.getResult();
        }

        NewBeeMallShoppingCartItem tempShoppingCartItemByDB = shoppingCartItemMapper.selectByUserIdAndGoodsId(userId, goodsId);
        if (tempShoppingCartItemByDB == null) {
            //插入购物车
            int number = shoppingCartItemMapper.insertSelective(newBeeMallShoppingCartItem);
            return (number > 0) ? ServiceResultEnum.SUCCESS.getResult() : ServiceResultEnum.DB_ERROR.getResult();

        }else {
            //更新购物车
            return updateNewBeeMallCartItem(newBeeMallShoppingCartItem);
        }
    }

    /**
     * 修改购物车中的属性
     *
     * @param newBeeMallShoppingCartItem
     * @return
     */
    @Override
    public String updateNewBeeMallCartItem(NewBeeMallShoppingCartItem newBeeMallShoppingCartItem) {
        NewBeeMallShoppingCartItem newBeeMallShoppingCartItemUpdate = shoppingCartItemMapper.selectByPrimaryKey(newBeeMallShoppingCartItem.getCartItemId());
        if (newBeeMallShoppingCartItemUpdate == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        //超出单个商品的最大数量
        if (newBeeMallShoppingCartItem.getGoodsCount() > Constants.SHOPPING_CART_ITEM_LIMIT_NUMBER) {
            return ServiceResultEnum.SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR.getResult();
        }
        //当前登录账号的userId与待修改的cartItem中userId不同，返回错误
        if (!newBeeMallShoppingCartItemUpdate.getUserId().equals(newBeeMallShoppingCartItem.getUserId())) {
            return ServiceResultEnum.NO_PERMISSION_ERROR.getResult();
        }
        //数值相同，则不执行数据操作
        if (newBeeMallShoppingCartItem.getGoodsCount().equals(newBeeMallShoppingCartItemUpdate.getGoodsCount())) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        newBeeMallShoppingCartItemUpdate.setGoodsCount(newBeeMallShoppingCartItem.getGoodsCount());
        newBeeMallShoppingCartItemUpdate.setUpdateTime(new Date());
        //修改记录
        if (shoppingCartItemMapper.updateByPrimaryKeySelective(newBeeMallShoppingCartItemUpdate) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    /**
     * 获取购物车详情
     *
     * @param newBeeMallShoppingCartItemId
     * @return
     */
    @Override
    public NewBeeMallShoppingCartItem getNewBeeMallCartItemById(Long newBeeMallShoppingCartItemId) {
        return shoppingCartItemMapper.selectByPrimaryKey(newBeeMallShoppingCartItemId);
    }

    /**
     * 删除购物车中的商品
     *
     * @param shoppingCartItemId
     * @param userId
     * @return
     */
    @Override
    public Boolean deleteById(Long shoppingCartItemId, Long userId) {
        NewBeeMallShoppingCartItem newBeeMallShoppingCartItem = shoppingCartItemMapper.selectByPrimaryKey(shoppingCartItemId);
        if (newBeeMallShoppingCartItem == null) {
            return false;
        }
        //userId不同不能删除
        if (!userId.equals(newBeeMallShoppingCartItem.getUserId())) {
            return false;
        }
        return shoppingCartItemMapper.deleteByPrimaryKey(shoppingCartItemId) > 0;
    }

    /**
     * 获取我的购物车的列表数据
     *
     * @param newBeeMallUserId
     * @return
     */
    @Override
    public List<NewBeeMallShoppingCartItemVO> getMyShoppingCartItems(Long newBeeMallUserId) {
        List<NewBeeMallShoppingCartItemVO> newBeeMallShoppingCartItemVOS = new ArrayList<>();
        List<NewBeeMallShoppingCartItem> newBeeMallShoppingCartItems = shoppingCartItemMapper.selectByUserId(newBeeMallUserId, Constants.SHOPPING_CART_ITEM_TOTAL_NUMBER);
        if (!CollectionUtils.isEmpty(newBeeMallShoppingCartItems)) {
            //查询商品信息并做数据转换
            List<Long> newBeeMallGoodsIds = newBeeMallShoppingCartItems.stream().map(NewBeeMallShoppingCartItem::getGoodsId).collect(Collectors.toList());
            List<NewBeeMallGoods> newBeeMallGoods = goodsMapper.selectByPrimaryKeys(newBeeMallGoodsIds);
            Map<Long, NewBeeMallGoods> newBeeMallGoodsMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(newBeeMallGoods)) {
                newBeeMallGoodsMap = newBeeMallGoods.stream().collect(Collectors.toMap(NewBeeMallGoods::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
            }
            for (NewBeeMallShoppingCartItem newBeeMallShoppingCartItem : newBeeMallShoppingCartItems) {
                NewBeeMallShoppingCartItemVO newBeeMallShoppingCartItemVO = new NewBeeMallShoppingCartItemVO();
                BeanUtil.copyProperties(newBeeMallShoppingCartItem, newBeeMallShoppingCartItemVO);
                if (newBeeMallGoodsMap.containsKey(newBeeMallShoppingCartItem.getGoodsId())) {
                    NewBeeMallGoods newBeeMallGoodsTemp = newBeeMallGoodsMap.get(newBeeMallShoppingCartItem.getGoodsId());
                    newBeeMallShoppingCartItemVO.setGoodsCoverImg(newBeeMallGoodsTemp.getGoodsCoverImg());
                    String goodsName = newBeeMallGoodsTemp.getGoodsName();
                    // 字符串过长导致文字超出的问题
                    if (goodsName.length() > 28) {
                        goodsName = goodsName.substring(0, 28) + "...";
                    }
                    newBeeMallShoppingCartItemVO.setGoodsName(goodsName);
                    newBeeMallShoppingCartItemVO.setSellingPrice(newBeeMallGoodsTemp.getSellingPrice());
                    newBeeMallShoppingCartItemVOS.add(newBeeMallShoppingCartItemVO);
                }
            }
        }
        return newBeeMallShoppingCartItemVOS;
    }
}
