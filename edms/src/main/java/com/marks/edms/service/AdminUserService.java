package com.marks.edms.service;

import com.marks.edms.entity.AdminUser;

public interface AdminUserService {
    /**
     * @param username
     * @param password
     * @return
     */
    AdminUser login(String username, String password);

    AdminUser getUserDetailById(Integer loginUserId);

    Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
