package com.marks.edms.dao;

import com.marks.edms.entity.NewBeeMallOrderItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewBeeMallOrderItemMapperTest {

    @Autowired
    private NewBeeMallOrderItemMapper orderItemMapper;

    @Transactional
    @Test
    void deleteByPrimaryKey() {
        Long orderId = 1L;
        int number = orderItemMapper.deleteByPrimaryKey(orderId);
        Assertions.assertEquals(true,number>0, "deleteByPrimaryKey失败");
    }

    @Transactional
    @Test
    void insert() {
        NewBeeMallOrderItem record = new NewBeeMallOrderItem();
        record.setOrderId(20L);
        record.setGoodsId(10895L);
        record.setGoodsName("HUAWEI Mate 30 4000万超感光徕卡影像");
        record.setGoodsCoverImg("/goods-img/mate30-3.png");
        record.setSellingPrice(2000);
        record.setGoodsCount(1);
        record.setCreateTime(new Date());

        int number = orderItemMapper.insert(record);
        Assertions.assertEquals(true,number>0, "insert失败");

    }

    @Transactional
    @Test
    void insertSelective() {
        NewBeeMallOrderItem record = new NewBeeMallOrderItem();
        record.setOrderItemId(35L);
        record.setOrderId(20L);
        record.setGoodsId(10895L);
        record.setGoodsName("HUAWEI Mate 30 4000万超感光徕卡影像");
        record.setGoodsCoverImg("/goods-img/mate30-3.png");
        record.setSellingPrice(2000);
        record.setGoodsCount(1);
        record.setCreateTime(new Date());

        int number = orderItemMapper.insertSelective(record);
        Assertions.assertEquals(true,number>0, "insert失败");
    }

    @Transactional
    @Test
    void selectByPrimaryKey() {
        Long orderId = 1L;
        NewBeeMallOrderItem orderItem = orderItemMapper.selectByPrimaryKey(orderId);
        Assertions.assertEquals(true,orderItem != null, "selectByPrimaryKey失败");
    }

    @Transactional
    @Test
    void selectByOrderId() {
        Long orderId = 9L;
        List<NewBeeMallOrderItem> newBeeMallOrderItems = orderItemMapper.selectByOrderId(orderId);
        Assertions.assertEquals(true,newBeeMallOrderItems.size() > 0, "selectByOrderId");
    }

    @Transactional
    @Test
    void selectByOrderIds() {
        List<Long> orderIds =new ArrayList<>();
        orderIds.add(1L);
        orderIds.add(2L);
        orderIds.add(3L);
        orderIds.add(4L);
        List<NewBeeMallOrderItem> newBeeMallOrderItems = orderItemMapper.selectByOrderIds(orderIds);
        Assertions.assertEquals(true,newBeeMallOrderItems.size() > 0, "selectByOrderIds");
    }

    @Transactional
    @Test
    void insertBatch() {
        List<NewBeeMallOrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            NewBeeMallOrderItem record = new NewBeeMallOrderItem();
            record.setOrderId(20L);
            record.setGoodsId(10895L);
            record.setGoodsName("HUAWEI Mate 30 4000万超感光徕卡影像");
            record.setGoodsCoverImg("/goods-img/mate30-3.png");
            record.setSellingPrice(2000);
            record.setGoodsCount(1);
            record.setCreateTime(new Date());
            orderItems.add(record);
        }
        int number = orderItemMapper.insertBatch(orderItems);
        Assertions.assertEquals(true,number>0, "insertBatch 失败");
    }

    @Transactional
    @Test
    void updateByPrimaryKey() {
        NewBeeMallOrderItem record = new NewBeeMallOrderItem();
        record.setOrderItemId(1L);
        record.setOrderId(1L);
        record.setGoodsId(10895L);
        record.setGoodsName("HUAWEI Mate 30 4000万超感光徕卡影像");
        record.setGoodsCoverImg("/goods-img/mate30-3.png");
        record.setSellingPrice(2000);
        record.setGoodsCount(1);
        record.setCreateTime(new Date());
        int number = orderItemMapper.updateByPrimaryKey(record);
        Assertions.assertEquals(true,number>0, "updateByPrimaryKey 失败");
    }

    @Transactional
    @Test
    void updateByPrimaryKeySelective() {
        NewBeeMallOrderItem record = new NewBeeMallOrderItem();
        record.setOrderItemId(1L);
        record.setOrderId(1L);
        record.setGoodsId(10895L);
        record.setGoodsName("HUAWEI Mate 30 4000万超感光徕卡影像");
        record.setGoodsCoverImg("/goods-img/mate30-3.png");
        record.setSellingPrice(2000);
        record.setGoodsCount(1);
        record.setCreateTime(new Date());
        int number = orderItemMapper.updateByPrimaryKeySelective(record);
        Assertions.assertEquals(true,number>0, "updateByPrimaryKeySelective 失败");

    }
}