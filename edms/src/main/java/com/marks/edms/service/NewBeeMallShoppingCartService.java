package com.marks.edms.service;

import com.marks.edms.controller.vo.NewBeeMallShoppingCartItemVO;
import com.marks.edms.entity.NewBeeMallShoppingCartItem;

import java.util.List;

public interface NewBeeMallShoppingCartService {
    /**
     * 保存商品至购物车
     * @param newBeeMallShoppingCartItem
     * @return
     */
    String saveNewBeeMallCartItem(NewBeeMallShoppingCartItem newBeeMallShoppingCartItem);

    /**
     * 修改购物车中的属性
     * @param newBeeMallShoppingCartItem
     * @return
     */
    String updateNewBeeMallCartItem(NewBeeMallShoppingCartItem newBeeMallShoppingCartItem);

    /**
     * 获取购物车详情
     * @param newBeeMallShoppingCartItemId
     * @return
     */
    NewBeeMallShoppingCartItem getNewBeeMallCartItemById(Long newBeeMallShoppingCartItemId);

    /**
     * 删除购物车中的商品
     * @param shoppingCartItemId
     * @param userId
     * @return
     */
    Boolean deleteById(Long shoppingCartItemId, Long userId);

    /**
     * 获取我的购物车的列表数据
     * @param newBeeMallUserId
     * @return
     */
    List<NewBeeMallShoppingCartItemVO> getMyShoppingCartItems(Long newBeeMallUserId);
}
