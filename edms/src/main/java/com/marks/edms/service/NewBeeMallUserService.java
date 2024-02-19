package com.marks.edms.service;

import com.marks.edms.controller.vo.NewBeeMallUserVO;
import com.marks.edms.entity.MallUser;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;

import javax.servlet.http.HttpSession;

public interface NewBeeMallUserService {
    /**
     * 后台分页
     * @param pageUtil
     * @return
     */
    PageResult getNewBeeMallUsersPages(PageQueryUtil pageUtil);

    /**
     * 用户注册
     * @param loginName
     * @param password
     * @return
     */

    String register(String loginName, String password, String userEmail);

    /**
     * 登录
     * @param loginName
     * @param passwordMD5
     * @param httpSession
     * @return
     */
    String login(String loginName, String passwordMD5, HttpSession httpSession);

    /**
     * 用户信息修改并返回最新的用户信息
     * @param mallUser
     * @param httpSession
     * @return
     */
    NewBeeMallUserVO updateUserInfo(MallUser mallUser, HttpSession httpSession);

    /**
     * 用户禁用与解除用户禁用(0-未锁定 1-已锁定)
     * @param ids
     * @param lockStatus
     * @return
     */
    Boolean lockUsers(Long[] ids, int lockStatus);

    String updateUserPassword(String oldPassword, String newPassword, HttpSession httpSession);
}
