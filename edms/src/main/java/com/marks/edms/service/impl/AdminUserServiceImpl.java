package com.marks.edms.service.impl;

import com.marks.edms.dao.AdminUserMapper;
import com.marks.edms.entity.AdminUser;
import com.marks.edms.service.AdminUserService;
import com.marks.edms.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public AdminUser login(String username, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.login(username,passwordMd5);
    }
}
