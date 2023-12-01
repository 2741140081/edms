package com.marks.edms.service.impl;

import com.marks.edms.controller.common.ServiceResultEnum;
import com.marks.edms.controller.vo.NewBeeMallUserVO;
import com.marks.edms.dao.MallUserMapper;
import com.marks.edms.entity.MallUser;
import com.marks.edms.service.NewBeeMallUserService;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import com.marks.edms.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class NewBeeMallUserServiceImpl implements NewBeeMallUserService {
    @Autowired
    private MallUserMapper userMapper;
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    @Override
    public PageResult getNewBeeMallUsersPages(PageQueryUtil pageUtil) {
        List<MallUser> mallUserList = userMapper.findMallUserList(pageUtil);
        int totalMallUsers = userMapper.getTotalMallUsers(pageUtil);
        PageResult result = new PageResult(totalMallUsers, pageUtil.getLimit(), pageUtil.getPage(), mallUserList);
        return result;
    }

    /**
     * 用户注册
     *
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public String register(String loginName, String password) {
        // 判断用户是否存在
        MallUser mallUser = userMapper.selectByLoginName(loginName);
        if (mallUser != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();

        }
        // MD5加密密码

        MallUser registerUser = new MallUser();

        return null;
    }

    /**
     * 登录
     *
     * @param loginName
     * @param passwordMD5
     * @param httpSession
     * @return
     */
    @Override
    public String login(String loginName, String passwordMD5, HttpSession httpSession) {
        return null;
    }

    /**
     * 用户信息修改并返回最新的用户信息
     *
     * @param mallUser
     * @param httpSession
     * @return
     */
    @Override
    public NewBeeMallUserVO updateUserInfo(MallUser mallUser, HttpSession httpSession) {
        return null;
    }

    /**
     * 用户禁用与解除用户禁用(0-未锁定 1-已锁定)
     *
     * @param ids
     * @param lockStatus
     * @return
     */
    @Override
    public Boolean lockUsers(Long[] ids, int lockStatus) {
        return null;
    }
}
