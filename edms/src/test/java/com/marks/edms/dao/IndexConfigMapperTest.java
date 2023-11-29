package com.marks.edms.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IndexConfigMapperTest {

    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Transactional
    @Test
    void deleteByPrimaryKey() {
        Long configId = 26L;
        int deleteItemNumber = indexConfigMapper.deleteByPrimaryKey(configId);
        Assertions.assertEquals(1, deleteItemNumber,"删除数据错误，数据条数为"+ deleteItemNumber +", configId:" + configId);

    }

    @Transactional
    @Test
    void insert() {
    }

    @Transactional
    @Test
    void insertSelective() {
    }

    @Transactional
    @Test
    void selectByPrimaryKey() {
    }

    @Transactional
    @Test
    void selectByTypeAndGoodsId() {
    }

    @Transactional
    @Test
    void updateByPrimaryKeySelective() {
    }

    @Transactional
    @Test
    void updateByPrimaryKey() {
    }

    @Transactional
    @Test
    void findIndexConfigList() {
    }

    @Transactional
    @Test
    void getTotalIndexConfigs() {
    }

    @Transactional
    @Test
    void deleteBatch() {
    }

    @Transactional
    @Test
    void findIndexConfigsByTypeAndNum() {
    }

}