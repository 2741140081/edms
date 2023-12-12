package com.marks.edms.dao;

import com.marks.edms.entity.NewBeeMallOrder;
import com.marks.edms.util.PageQueryUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewBeeMallOrderMapperTest {

    @Autowired
    private NewBeeMallOrderMapper orderMapper;
    @Transactional
    @Test
    void deleteByPrimaryKey() {
        Long orderId = 1L;
        int number = orderMapper.deleteByPrimaryKey(orderId);
        Assertions.assertEquals(true,number>0, "deleteByPrimaryKey失败");
    }

    @Transactional
    @Test
    void insert() {
        NewBeeMallOrder record = new NewBeeMallOrder();
//        record.setOrderId(21L); orderId为自增主键
        record.setOrderNo("15702847670935185");
        record.setUserId(6L);
        record.setTotalPrice(6000);
        record.setPayStatus((byte) 0);
        record.setPayType((byte) 1);
        record.setPayTime(new Date());
        record.setOrderStatus((byte) 0);
        record.setExtraInfo("Test extra info");
        record.setUserName("Tester");
        record.setUserPhone("13117806085");
        record.setUserAddress("上海浦东新区1路1号 Tester 13724577797");
        record.setIsDeleted((byte) 0);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());

        int number = orderMapper.insert(record);
        Assertions.assertEquals(true,number>0, "insert失败");
    }

    @Transactional
    @Test
    void insertSelective() {
        NewBeeMallOrder record = new NewBeeMallOrder();
        record.setOrderId(21L); //orderId为自增主键
        record.setOrderNo("15702847670935185");
        record.setUserId(6L);
        record.setTotalPrice(6000);
        record.setPayStatus((byte) 0);
        record.setPayType((byte) 1);
        record.setPayTime(new Date());
        record.setOrderStatus((byte) 0);
        record.setExtraInfo("Test extra info");
        record.setUserName("Tester");
        record.setUserPhone("13117806085");
        record.setUserAddress("上海浦东新区1路1号 Tester 13724577797");
        record.setIsDeleted((byte) 0);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());

        int number = orderMapper.insertSelective(record);
        Assertions.assertEquals(true,number>0, "insertSelective 失败");
    }

    @Transactional
    @Test
    void selectByPrimaryKey() {
        Long orderId = 1L;
        NewBeeMallOrder order = orderMapper.selectByPrimaryKey(orderId);
        Assertions.assertEquals(true,order != null, "selectByPrimaryKey失败");
    }

    @Transactional
    @Test
    void selectByOrderNo() {
        String orderNo = "15702847670935185";
        NewBeeMallOrder order = orderMapper.selectByOrderNo(orderNo);
        Assertions.assertEquals(true,order != null, "selectByOrderNo 失败");
    }

    @Transactional
    @Test
    void updateByPrimaryKeySelective() {
        NewBeeMallOrder record = new NewBeeMallOrder();
        record.setOrderId(1L); //orderId为自增主键
        record.setOrderNo("15702847670935185");
        record.setUserId(6L);
        record.setTotalPrice(6000);
        record.setPayStatus((byte) 0);
        record.setPayType((byte) 1);
        record.setPayTime(new Date());
        record.setOrderStatus((byte) 0);
        record.setExtraInfo("Test extra info");
        record.setUserName("Tester");
        record.setUserPhone("13117806085");
        record.setUserAddress("上海浦东新区1路1号 Tester 13724577797");
        record.setIsDeleted((byte) 0);
//        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int number = orderMapper.updateByPrimaryKeySelective(record);
        Assertions.assertEquals(true,number>0, "updateByPrimaryKeySelective 失败");
    }

    @Transactional
    @Test
    void updateByPrimaryKey() {
        NewBeeMallOrder record = new NewBeeMallOrder();
        record.setOrderId(1L); //orderId为自增主键
        record.setOrderNo("15702847670935185");
        record.setUserId(6L);
        record.setTotalPrice(6000);
        record.setPayStatus((byte) 0);
        record.setPayType((byte) 1);
        record.setPayTime(new Date());
        record.setOrderStatus((byte) 0);
        record.setExtraInfo("Test extra info");
        record.setUserName("Tester");
        record.setUserPhone("13117806085");
        record.setUserAddress("上海浦东新区1路1号 Tester 13724577797");
        record.setIsDeleted((byte) 0);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        int number = orderMapper.updateByPrimaryKey(record);
        Assertions.assertEquals(true,number>0, "updateByPrimaryKey 失败");
    }

    @Transactional
    @Test
    void findNewBeeMallOrderList() {
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("limit",10);
        params.put("start",0);
        params.put("userId", 1L);
        params.put("orderStatus", (byte) 1);
        params.put("isDeleted", (byte) 0);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        List<NewBeeMallOrder> newBeeMallOrderList = orderMapper.findNewBeeMallOrderList(pageUtil);
        Assertions.assertEquals(true,newBeeMallOrderList.size()>0, "findNewBeeMallOrderList 失败");
    }

    @Transactional
    @Test
    void getTotalNewBeeMallOrders() {
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("limit",10);
        params.put("start",0);
        params.put("userId", 1L);
        params.put("orderStatus", (byte) 1);
        params.put("isDeleted", (byte) 0);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        int totalNumber = orderMapper.getTotalNewBeeMallOrders(pageUtil);
        Assertions.assertEquals(true,totalNumber>0, "getTotalNewBeeMallOrders 失败");

    }

    @Transactional
    @Test
    void selectByPrimaryKeys() {
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(1L);
        orderIds.add(2L);
        orderIds.add(3L);
        orderIds.add(4L);
        orderIds.add(5L);
        orderIds.add(6L);
        List<NewBeeMallOrder> newBeeMallOrders = orderMapper.selectByPrimaryKeys(orderIds);
        Assertions.assertEquals(true,orderIds.size() == newBeeMallOrders.size(), "selectByPrimaryKeys 失败");
    }

    @Transactional
    @Test
    void checkOut() {
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(1L);
        orderIds.add(2L);
        orderIds.add(3L);
        orderIds.add(4L);
        orderIds.add(5L);
        orderIds.add(6L);
        int number = orderMapper.checkOut(orderIds);
        Assertions.assertEquals(true,number>0, "checkOut 失败");
    }

    @Transactional
    @Test
    void closeOrder() {
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(1L);
        orderIds.add(2L);
        orderIds.add(3L);
        orderIds.add(4L);
        orderIds.add(5L);
        orderIds.add(6L);
        int number = orderMapper.closeOrder(orderIds, (byte) -3);
        Assertions.assertEquals(true,number>0, "closeOrder 失败");
    }

    @Transactional
    @Test
    void checkDone() {
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(1L);
        orderIds.add(2L);
        orderIds.add(3L);
        orderIds.add(4L);
        orderIds.add(5L);
        orderIds.add(6L);
        int number = orderMapper.checkDone(orderIds);
        Assertions.assertEquals(true,number>0, "checkDone 失败");
    }
}