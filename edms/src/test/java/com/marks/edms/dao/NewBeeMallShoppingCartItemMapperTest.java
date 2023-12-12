package com.marks.edms.dao;

import com.marks.edms.entity.NewBeeMallShoppingCartItem;
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
class NewBeeMallShoppingCartItemMapperTest {

    @Autowired
    private NewBeeMallShoppingCartItemMapper shoppingCartItemMapper;

    @Transactional
    @Test
    void deleteByPrimaryKey() {
        Long cartItemId = 1L;
        int number = shoppingCartItemMapper.deleteByPrimaryKey(cartItemId);
        Assertions.assertEquals(true,number>0, "deleteByPrimaryKey失败");
    }

    @Transactional
    @Test
    void insert() {
        NewBeeMallShoppingCartItem record = new NewBeeMallShoppingCartItem();
        record.setCartItemId(2L);
        record.setUserId(19L);
        record.setGoodsId(10182L);
        record.setGoodsCount(1);
        record.setIsDeleted((byte) 0);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int number = shoppingCartItemMapper.insert(record);
        Assertions.assertEquals(true,number>0, "insert 失败");

    }

    @Transactional
    @Test
    void insertSelective() {
        NewBeeMallShoppingCartItem record = new NewBeeMallShoppingCartItem();
        record.setCartItemId(2L);
        record.setUserId(19L);
        record.setGoodsId(10182L);
        record.setGoodsCount(1);
        record.setIsDeleted((byte) 0);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int number = shoppingCartItemMapper.insertSelective(record);
        Assertions.assertEquals(true,number>0, "insertSelective 失败");
    }

    @Transactional
    @Test
    void selectByPrimaryKey() {
        Long cartItemId = 1L;
        NewBeeMallShoppingCartItem newBeeMallShoppingCartItem = shoppingCartItemMapper.selectByPrimaryKey(cartItemId);
        Assertions.assertEquals(true,newBeeMallShoppingCartItem != null, "selectByPrimaryKey 失败");
    }

    @Transactional
    @Test
    void selectByUserIdAndGoodsId() {
        Long newBeeMallUserId = 13L;
        Long goodsId = 10180L;
        NewBeeMallShoppingCartItem newBeeMallShoppingCartItem = shoppingCartItemMapper.selectByUserIdAndGoodsId(newBeeMallUserId, goodsId);
        Assertions.assertEquals(true,newBeeMallShoppingCartItem != null, "selectByUserIdAndGoodsId 失败");
    }

    @Transactional
    @Test
    void selectByUserId() {
        Long newBeeMallUserId = 13L;
        int number = 1;
        List<NewBeeMallShoppingCartItem> newBeeMallShoppingCartItems = shoppingCartItemMapper.selectByUserId(newBeeMallUserId, number);
        Assertions.assertEquals(true,newBeeMallShoppingCartItems.size() == 1, "selectByUserId 失败");
    }

    @Transactional
    @Test
    void selectCountByUserId() {
        Long newBeeMallUserId = 13L;
        int number = shoppingCartItemMapper.selectCountByUserId(newBeeMallUserId);
        Assertions.assertEquals(true,number>0, "selectCountByUserId 失败");
    }

    @Transactional
    @Test
    void updateByPrimaryKeySelective() {
        NewBeeMallShoppingCartItem record = new NewBeeMallShoppingCartItem();
        record.setCartItemId(1L);
        record.setUserId(13L);
        record.setGoodsId(10180L);
        record.setGoodsCount(1);
        record.setIsDeleted((byte) 0);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int number = shoppingCartItemMapper.updateByPrimaryKeySelective(record);
        Assertions.assertEquals(true,number>0, "updateByPrimaryKeySelective 失败");
    }

    @Transactional
    @Test
    void updateByPrimaryKey() {
        NewBeeMallShoppingCartItem record = new NewBeeMallShoppingCartItem();
        record.setCartItemId(1L);
        record.setUserId(13L);
        record.setGoodsId(10180L);
        record.setGoodsCount(1);
        record.setIsDeleted((byte) 0);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int number = shoppingCartItemMapper.updateByPrimaryKeySelective(record);
        Assertions.assertEquals(true,number>0, "updateByPrimaryKey 失败");
    }

    @Transactional
    @Test
    void deleteBatch() {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        int number = shoppingCartItemMapper.deleteBatch(ids);
        Assertions.assertEquals(true,number>0, "deleteBatch 失败");
    }
}