package com.marks.edms.dao;

import com.marks.edms.entity.NewBeeMallOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewBeeMallOrderItemMapper {
    int deleteByPrimaryKey(Long orderItemId);

    int insert(NewBeeMallOrderItem record);

    int insertSelective(NewBeeMallOrderItem record);

    NewBeeMallOrderItem selectByPrimaryKey(Long orderItemId);

    /**
     * 根据订单id获取订单项列表
     * @param orderId
     * @return
     */
    List<NewBeeMallOrderItem> selectByOrderId(Long orderId);

    /**
     * 根据订单ids获取订单项列表
     * @param orderIds
     * @return
     */
    List<NewBeeMallOrderItem> selectByOrderIds(@Param("orderIds") List<Long> orderIds);

    /**
     * 批量插入订单项数据
     * @param orderItems
     * @return
     */
    int insertBatch(@Param("orderItems") List<NewBeeMallOrderItem> orderItems);

    int updateByPrimaryKey(NewBeeMallOrderItem record);

    int updateByPrimaryKeySelective(NewBeeMallOrderItem record);
}
