package com.marks.edms.dao;

import com.marks.edms.entity.MallUser;
import com.marks.edms.util.PageQueryUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MallUserMapperTest {

    @Autowired
    private MallUserMapper userMapper;

    @Transactional
    @Test
    void deleteByPrimaryKey() {
        Long userId = 13L;
        int deleteNumber = userMapper.deleteByPrimaryKey(userId);
        Assertions.assertEquals(1, deleteNumber,"删除数据错误，数据条数为"+ deleteNumber);

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

    @Transactional
    @Test
    void insertSelective() {
        MallUser mallUser = new MallUser();
        mallUser.setUserId(18L);
        mallUser.setNickName("Marks22");
        mallUser.setLoginName("13000006085");
        mallUser.setPasswordMd5("e10adc3949ba59abbe56e057f20f883e");
        mallUser.setIntroduceSign("Test Sign");
        mallUser.setAddress("GZ");
        mallUser.setIsDeleted((byte) 1);
        mallUser.setLockedFlag((byte) 1);
        mallUser.setCreateTime(new Date());
        int number = userMapper.insert(mallUser);
        Assertions.assertEquals(1, number,"插入数据错误，数据条数为"+ number);
    }

    @Test
    void selectByPrimaryKey() {
        Long userId = 13L;
        MallUser mallUser = userMapper.selectByPrimaryKey(userId);
        System.out.println(mallUser.toString());
    }

    @Test
    void selectByLoginName() {
        String loginName = "13000006085";
        MallUser mallUser = userMapper.selectByLoginName(loginName);
        System.out.println(mallUser.toString());
    }

    @Test
    void selectByLoginNameAndPasswd() {
        String loginName = "13000006085";
        String password = "e10adc3949ba59abbe56e057f20f883e";
        MallUser mallUser = userMapper.selectByLoginNameAndPasswd(loginName, password);
        System.out.println(mallUser.toString());
    }

    @Transactional
    @Test
    void updateByPrimaryKeySelective() {
        MallUser mallUser = new MallUser();
        mallUser.setUserId(13L);
        mallUser.setIsDeleted((byte) 0);
        mallUser.setLockedFlag((byte) 0);
        mallUser.setCreateTime(new Date());
        int number = userMapper.updateByPrimaryKeySelective(mallUser);
        Assertions.assertEquals(1, number,"更新数据错误，数据条数为"+ number);
    }
    @Transactional
    @Test
    void updateByPrimaryKey() {
        MallUser mallUser = new MallUser();
        mallUser.setUserId(13L);
        mallUser.setNickName("Marks_Update_2");
        mallUser.setLoginName("13000006085");
        mallUser.setPasswordMd5("e10adc3949ba59abbe56e057f20f883e");
        mallUser.setIntroduceSign("Test Sign");
        mallUser.setAddress("GZ");
        mallUser.setIsDeleted((byte) 0);
        mallUser.setLockedFlag((byte) 0);
        mallUser.setCreateTime(new Date());
        int number = userMapper.updateByPrimaryKey(mallUser);
        Assertions.assertEquals(1, number,"插入数据错误，数据条数为"+ number);
    }

    @Test
    void findMallUserList() {
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("limit",10);
        params.put("start",0);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        List<MallUser> mallUserList = userMapper.findMallUserList(pageUtil);
        for (MallUser mallUser : mallUserList) {
            System.out.println(mallUser.toString());
        }
    }

    @Test
    void getTotalMallUsers() {
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("limit",10);
        params.put("start",0);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        int totalMallUsers = userMapper.getTotalMallUsers(pageUtil);
        Boolean flag = (totalMallUsers>1);
        Assertions.assertEquals(true, flag,"查询数据错误，数据条数为"+ totalMallUsers);
    }
    @Transactional
    @Test
    void lockUserBatch() {
        Long[] ids = {1L, 6L, 7L, 8L, 13L};
        int lockStatus = 1;
        int number = userMapper.lockUserBatch(ids, lockStatus);
        Assertions.assertEquals(5, number,"Lock数据错误，数据条数为"+ number);
    }
}