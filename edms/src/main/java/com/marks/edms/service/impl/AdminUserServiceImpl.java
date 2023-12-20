package com.marks.edms.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.marks.edms.dao.AdminUserMapper;
import com.marks.edms.entity.AdminUser;
import com.marks.edms.service.AdminUserService;
import com.marks.edms.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DS("mall")
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

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        System.out.println(loginUserId);
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);

        if (adminUser != null) {
            //当前用户不为空时，才能修改字段
            String originalPasswordMd5Code = MD5Util.MD5Encode(originalPassword, "UTF-8");

            if (originalPasswordMd5Code.equals(adminUser.getLoginPassword())) {
                adminUser.setLoginPassword(originalPasswordMd5Code);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);

        if (adminUser != null) {
            //当前用户不为空时，才能修改字段
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                return true;
            }
        }
        return false;
    }
}
