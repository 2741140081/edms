package com.marks.edms.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.marks.edms.common.Constants;
import com.marks.edms.common.NewBeeMallException;
import com.marks.edms.common.NewBeeMallOrderStatusEnum;
import com.marks.edms.common.ServiceResultEnum;
import com.marks.edms.controller.vo.NewBeeMallOrderDetailVO;
import com.marks.edms.controller.vo.NewBeeMallOrderItemVO;
import com.marks.edms.controller.vo.NewBeeMallShoppingCartItemVO;
import com.marks.edms.controller.vo.NewBeeMallUserVO;
import com.marks.edms.dao.NewBeeMallGoodsMapper;
import com.marks.edms.dao.NewBeeMallOrderItemMapper;
import com.marks.edms.dao.NewBeeMallOrderMapper;
import com.marks.edms.dao.NewBeeMallShoppingCartItemMapper;
import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.entity.NewBeeMallOrder;
import com.marks.edms.entity.NewBeeMallOrderItem;
import com.marks.edms.entity.StockNumDTO;
import com.marks.edms.service.NewBeeMallOrderService;
import com.marks.edms.util.BeanUtil;
import com.marks.edms.util.NumberUtil;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NewBeeMallOrderServiceImpl implements NewBeeMallOrderService {

    @Autowired
    private NewBeeMallOrderMapper newBeeMallOrderMapper;
    @Autowired
    private NewBeeMallOrderItemMapper newBeeMallOrderItemMapper;
    @Autowired
    private NewBeeMallShoppingCartItemMapper newBeeMallShoppingCartItemMapper;
    @Autowired
    private NewBeeMallGoodsMapper newBeeMallGoodsMapper;



    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    @Override
    public PageResult getNewBeeMallOrdersPage(PageQueryUtil pageUtil) {
        List<NewBeeMallOrder> newBeeMallOrders = newBeeMallOrderMapper.findNewBeeMallOrderList(pageUtil);
        int total = newBeeMallOrderMapper.getTotalNewBeeMallOrders(pageUtil);
        PageResult pageResult = new PageResult(total, pageUtil.getLimit(), pageUtil.getPage(), newBeeMallOrders);
        return pageResult;
    }

    /**
     * 订单信息修改
     *
     * @param newBeeMallOrder
     * @return
     */
    @Override
    @Transactional
    public String updateOrderInfo(NewBeeMallOrder newBeeMallOrder) {
        NewBeeMallOrder temp = newBeeMallOrderMapper.selectByPrimaryKey(newBeeMallOrder.getOrderId());
        //不为空且orderStatus>=0且状态为出库之前可以修改部分信息
        if (temp != null && temp.getOrderStatus() >= 0 && temp.getOrderStatus() < 3) {
            temp.setTotalPrice(newBeeMallOrder.getTotalPrice());
            temp.setUserAddress(newBeeMallOrder.getUserAddress());
            temp.setUpdateTime(new Date());
            if (newBeeMallOrderMapper.updateByPrimaryKeySelective(temp) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            }
            return ServiceResultEnum.DB_ERROR.getResult();
        }
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    /**
     * 配货
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public String checkDone(Long[] ids) {
        //查询所有的订单 判断状态 修改状态和更新时间
        List<NewBeeMallOrder> orders = newBeeMallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (NewBeeMallOrder newBeeMallOrder : orders) {
                if (newBeeMallOrder.getIsDeleted() == 1) {
                    errorOrderNos += newBeeMallOrder.getOrderNo() + " ";
                    continue;
                }
                if (newBeeMallOrder.getOrderStatus() != 1) {
                    errorOrderNos += newBeeMallOrder.getOrderNo() + " ";
                }
            }
            if (StringUtils.hasText(errorOrderNos)) {
                //订单状态正常 可以执行配货完成操作 修改订单状态和更新时间
                if (newBeeMallOrderMapper.checkDone(Arrays.asList(ids)) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                //订单此时不可执行出库操作
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "订单的状态不是支付成功无法执行出库操作";
                } else {
                    return "你选择了太多状态不是支付成功的订单，无法执行配货完成操作";
                }
            }
        }
        //未查询到数据 返回错误提示
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    /**
     * 出库
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public String checkOut(Long[] ids) {
        //查询所有的订单 判断状态 修改状态和更新时间
        List<NewBeeMallOrder> orders = newBeeMallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (NewBeeMallOrder newBeeMallOrder : orders) {
                if (newBeeMallOrder.getIsDeleted() == 1) {
                    errorOrderNos += newBeeMallOrder.getOrderNo() + " ";
                    continue;
                }
                if (newBeeMallOrder.getOrderStatus() != 1 && newBeeMallOrder.getOrderStatus() != 2) {
                    errorOrderNos += newBeeMallOrder.getOrderNo() + " ";
                }
            }
            if (StringUtils.isEmpty(errorOrderNos)) {
                //订单状态正常 可以执行出库操作 修改订单状态和更新时间
                if (newBeeMallOrderMapper.checkOut(Arrays.asList(ids)) > 0) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                //订单此时不可执行出库操作
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "订单的状态不是支付成功或配货完成无法执行出库操作";
                } else {
                    return "你选择了太多状态不是支付成功或配货完成的订单，无法执行出库操作";
                }
            }
        }
        //未查询到数据 返回错误提示
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    /**
     * 关闭订单
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public String closeOrder(Long[] ids) {
        //查询所有的订单 判断状态 修改状态和更新时间
        List<NewBeeMallOrder> orders = newBeeMallOrderMapper.selectByPrimaryKeys(Arrays.asList(ids));
        String errorOrderNos = "";
        if (!CollectionUtils.isEmpty(orders)) {
            for (NewBeeMallOrder newBeeMallOrder : orders) {
                // isDeleted=1 一定为已关闭订单
                if (newBeeMallOrder.getIsDeleted() == 1) {
                    errorOrderNos += newBeeMallOrder.getOrderNo() + " ";
                    continue;
                }
                //已关闭或者已完成无法关闭订单
                if (newBeeMallOrder.getOrderStatus() == 4 || newBeeMallOrder.getOrderStatus() < 0) {
                    errorOrderNos += newBeeMallOrder.getOrderNo() + " ";
                }
            }
            if (StringUtils.isEmpty(errorOrderNos)) {
                //订单状态正常 可以执行关闭操作 修改订单状态和更新时间&&恢复库存
                if (newBeeMallOrderMapper.closeOrder(Arrays.asList(ids), NewBeeMallOrderStatusEnum.ORDER_CLOSED_BY_JUDGE.getOrderStatus()) > 0 && recoverStockNum(Arrays.asList(ids))) {
                    return ServiceResultEnum.SUCCESS.getResult();
                } else {
                    return ServiceResultEnum.DB_ERROR.getResult();
                }
            } else {
                //订单此时不可执行关闭操作
                if (errorOrderNos.length() > 0 && errorOrderNos.length() < 100) {
                    return errorOrderNos + "订单不能执行关闭操作";
                } else {
                    return "你选择的订单不能执行关闭操作";
                }
            }
        }
        //未查询到数据 返回错误提示
        return ServiceResultEnum.DATA_NOT_EXIST.getResult();
    }

    private boolean recoverStockNum(List<Long> asList) {
        return false;
    }

    /**
     * 保存订单
     *
     * @param user
     * @param myShoppingCartItems
     * @return
     */
    @Override
    @Transactional
    public String saveOrder(NewBeeMallUserVO user, List<NewBeeMallShoppingCartItemVO> myShoppingCartItems) {
        List<Long> itemIdList = myShoppingCartItems.stream().map(NewBeeMallShoppingCartItemVO::getCartItemId).collect(Collectors.toList());
        List<Long> goodsIds = myShoppingCartItems.stream().map(NewBeeMallShoppingCartItemVO::getGoodsId).collect(Collectors.toList());
        List<NewBeeMallGoods> newBeeMallGoods = newBeeMallGoodsMapper.selectByPrimaryKeys(goodsIds);
        //检查是否包含已下架商品
        List<NewBeeMallGoods> goodsListNotSelling = newBeeMallGoods.stream()
                .filter(newBeeMallGoodsTemp -> newBeeMallGoodsTemp.getGoodsSellStatus() != Constants.SELL_STATUS_UP)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(goodsListNotSelling)) {
            //goodsListNotSelling 对象非空则表示有下架商品
            NewBeeMallException.fail(goodsListNotSelling.get(0).getGoodsName() + "已下架，无法生成订单");
        }
        Map<Long, NewBeeMallGoods> newBeeMallGoodsMap = newBeeMallGoods.stream().collect(Collectors.toMap(NewBeeMallGoods::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
        //判断商品库存
        for (NewBeeMallShoppingCartItemVO shoppingCartItemVO : myShoppingCartItems) {
            //查出的商品中不存在购物车中的这条关联商品数据，直接返回错误提醒
            if (!newBeeMallGoodsMap.containsKey(shoppingCartItemVO.getGoodsId())) {
                NewBeeMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
            }
            //存在数量大于库存的情况，直接返回错误提醒
            if (shoppingCartItemVO.getGoodsCount() > newBeeMallGoodsMap.get(shoppingCartItemVO.getGoodsId()).getStockNum()) {
                NewBeeMallException.fail(ServiceResultEnum.SHOPPING_ITEM_COUNT_ERROR.getResult());
            }
        }
        //删除购物项
        if (!CollectionUtils.isEmpty(itemIdList) && !CollectionUtils.isEmpty(goodsIds) && !CollectionUtils.isEmpty(newBeeMallGoods)) {
            if (newBeeMallShoppingCartItemMapper.deleteBatch(itemIdList) > 0) {
                List<StockNumDTO> stockNumDTOS = BeanUtil.copyList(myShoppingCartItems, StockNumDTO.class);
                int updateStockNumResult = newBeeMallGoodsMapper.updateStockNum(stockNumDTOS);
                if (updateStockNumResult < 1) {
                    NewBeeMallException.fail(ServiceResultEnum.SHOPPING_ITEM_COUNT_ERROR.getResult());
                }
                //生成订单号
                String orderNo = NumberUtil.genOrderNo();
                int priceTotal = 0;
                //保存订单
                NewBeeMallOrder newBeeMallOrder = new NewBeeMallOrder();
                newBeeMallOrder.setOrderNo(orderNo);
                newBeeMallOrder.setUserId(user.getUserId());
                newBeeMallOrder.setUserAddress(user.getAddress());
                //总价
                for (NewBeeMallShoppingCartItemVO newBeeMallShoppingCartItemVO : myShoppingCartItems) {
                    priceTotal += newBeeMallShoppingCartItemVO.getGoodsCount() * newBeeMallShoppingCartItemVO.getSellingPrice();
                }
                if (priceTotal < 1) {
                    NewBeeMallException.fail(ServiceResultEnum.ORDER_PRICE_ERROR.getResult());
                }
                newBeeMallOrder.setTotalPrice(priceTotal);
                //订单body字段，用来作为生成支付单描述信息，暂时未接入第三方支付接口，故该字段暂时设为空字符串
                String extraInfo = "";
                newBeeMallOrder.setExtraInfo(extraInfo);
                //生成订单项并保存订单项纪录
                if (newBeeMallOrderMapper.insertSelective(newBeeMallOrder) > 0) {
                    //生成所有的订单项快照，并保存至数据库
                    List<NewBeeMallOrderItem> newBeeMallOrderItems = new ArrayList<>();
                    for (NewBeeMallShoppingCartItemVO newBeeMallShoppingCartItemVO : myShoppingCartItems) {
                        NewBeeMallOrderItem newBeeMallOrderItem = new NewBeeMallOrderItem();
                        //使用BeanUtil工具类将newBeeMallShoppingCartItemVO中的属性复制到newBeeMallOrderItem对象中
                        BeanUtil.copyProperties(newBeeMallShoppingCartItemVO, newBeeMallOrderItem);
                        //NewBeeMallOrderMapper文件insert()方法中使用了useGeneratedKeys因此orderId可以获取到
                        newBeeMallOrderItem.setOrderId(newBeeMallOrder.getOrderId());
                        newBeeMallOrderItems.add(newBeeMallOrderItem);
                    }
                    //保存至数据库
                    if (newBeeMallOrderItemMapper.insertBatch(newBeeMallOrderItems) > 0) {
                        //所有操作成功后，将订单号返回，以供Controller方法跳转到订单详情
                        return orderNo;
                    }
                    NewBeeMallException.fail(ServiceResultEnum.ORDER_PRICE_ERROR.getResult());
                }
                NewBeeMallException.fail(ServiceResultEnum.DB_ERROR.getResult());
            }
            NewBeeMallException.fail(ServiceResultEnum.DB_ERROR.getResult());
        }
        NewBeeMallException.fail(ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult());
        return ServiceResultEnum.SHOPPING_ITEM_ERROR.getResult();
    }

    /**
     * 获取订单详情
     *
     * @param orderNo
     * @param userId
     * @return
     */
    @Override
    public NewBeeMallOrderDetailVO getOrderDetailByOrderNo(String orderNo, Long userId) {
        return null;
    }

    /**
     * 获取订单详情
     *
     * @param orderNo
     * @return
     */
    @Override
    public NewBeeMallOrder getNewBeeMallOrderByOrderNo(String orderNo) {
        return null;
    }

    /**
     * 我的订单列表
     *
     * @param pageUtil
     * @return
     */
    @Override
    public PageResult getMyOrders(PageQueryUtil pageUtil) {
        return null;
    }

    /**
     * 手动取消订单
     *
     * @param orderNo
     * @param userId
     * @return
     */
    @Override
    public String cancelOrder(String orderNo, Long userId) {
        return null;
    }

    /**
     * 确认收货
     *
     * @param orderNo
     * @param userId
     * @return
     */
    @Override
    public String finishOrder(String orderNo, Long userId) {
        return null;
    }

    /**
     * @param orderNo
     * @param payType
     * @return
     */
    @Override
    public String paySuccess(String orderNo, int payType) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<NewBeeMallOrderItemVO> getOrderItems(Long id) {
        return null;
    }
}
