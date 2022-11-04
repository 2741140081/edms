package com.marks.edms.dao;

import com.marks.edms.domain.SysUser;

public interface UserDao {
    public SysUser findByUserName(String username);
}
