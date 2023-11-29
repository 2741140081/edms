package com.marks.edms.dao;

import com.marks.edms.entity.MallUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MallUserMapperTest {

    @Autowired
    private MallUserMapper userMapper;

    @Test
    void deleteByPrimaryKey() {
    }

    @Transactional
    @Test
    void insert() {
        MallUser mallUser = new MallUser();
        mallUser.setUserId(10L);
        mallUser.setNickName("Marks");
        mallUser.setLoginName("13000006085");
        mallUser.setPasswordMd5("e10adc3949ba59abbe56e057f20f883e");
        mallUser.setIntroduceSign("Test Sign");
        mallUser.setAddress("GZ");
        mallUser.setIsDeleted((byte) 0);
        mallUser.setLockedFlag((byte) 0);
        mallUser.setCreateTime(new Date());
        int number = userMapper.insert(mallUser);
        Assertions.assertEquals(1, number,"插入数据错误，数据条数为"+ number);
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void selectByLoginName() {
    }

    @Test
    void selectByLoginNameAndPasswd() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void findMallUserList() {
    }

    @Test
    void getTotalMallUsers() {
    }

    @Test
    void lockUserBatch() {
    }
}