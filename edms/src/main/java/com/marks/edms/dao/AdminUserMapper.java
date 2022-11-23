package com.marks.edms.dao;

import com.marks.edms.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminUserMapper {
    AdminUser login(@Param("userName") String userName, @Param("password") String password);

    AdminUser selectByPrimaryKey(Integer loginUserId);

    Integer updateByPrimaryKeySelective(AdminUser adminUser);

}
