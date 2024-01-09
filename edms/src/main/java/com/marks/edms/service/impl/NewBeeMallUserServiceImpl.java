package com.marks.edms.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.marks.edms.common.Constants;
import com.marks.edms.common.ServiceResultEnum;
import com.marks.edms.controller.vo.NewBeeMallUserVO;
import com.marks.edms.dao.MallUserMapper;
import com.marks.edms.entity.MallUser;
import com.marks.edms.service.NewBeeMallUserService;
import com.marks.edms.util.BeanUtil;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
@DS("mall")
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
    public String register(String loginName, String password, String userEmail) {
        // 判断用户是否存在
        MallUser mallUser = userMapper.selectByLoginName(loginName);
        if (mallUser != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();

        }
        //encode()：对明文字符串进行加密
        //注册用户时，使用SHA-256+随机盐+密钥把用户输入的密码进行hash处理，得到密码的hash值，然后将其存入数据库中。
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        MallUser registerUser = new MallUser();
        String encryptedPassword = encoder.encode(password);
        registerUser.setLoginName(loginName);
        registerUser.setPasswordMd5(encryptedPassword);
        registerUser.setCreateTime(new Date());
        registerUser.setIsDeleted((byte) 0);
        registerUser.setLockedFlag((byte) 0);
        registerUser.setUserEmail(userEmail);
        registerUser.setPasswordUpdateTime(new Date());
        registerUser.setPasswordStatus('C');
        registerUser.setOldPassword1(encryptedPassword);
        int flag = userMapper.insertSelective(registerUser);
        if (flag > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        MallUser mallUser = userMapper.selectByLoginName(loginName);
        if (mallUser != null && httpSession != null) {
            // 判断用户密码是否一致
            boolean matchFlag = encoder.matches(passwordMD5, mallUser.getPasswordMd5());
            if (!matchFlag) {
                return ServiceResultEnum.LOGIN_PASSWORD_ERROR.getResult();
            }
            // 判断用户是否为锁定状态
            if (mallUser.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED.getResult();
            }
            // 判断用户是否需要进行更新密码, 'R'表示需要 Reset Password
            if (mallUser.getPasswordStatus() == 'R') {
                return ServiceResultEnum.USER_NEED_RESET_PASSWORD.getResult();
            }

            NewBeeMallUserVO newBeeMallUserVO = new NewBeeMallUserVO();
            BeanUtil.copyProperties(mallUser, newBeeMallUserVO);
            httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, newBeeMallUserVO);

            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.LOGIN_ERROR.getResult();

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
        NewBeeMallUserVO userTemp = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        MallUser currentUser = userMapper.selectByPrimaryKey(userTemp.getUserId());
        if (currentUser != null) {
            if (!ObjectUtils.isEmpty(mallUser.getAddress())) {
                currentUser.setAddress(mallUser.getAddress());
            }
            if (!ObjectUtils.isEmpty(mallUser.getIntroduceSign())) {
                currentUser.setIntroduceSign(mallUser.getIntroduceSign());
            }
            if (!ObjectUtils.isEmpty(mallUser.getNickName())) {
                currentUser.setNickName(mallUser.getNickName());
            }
            if (userMapper.updateByPrimaryKeySelective(currentUser) > 0) {
                NewBeeMallUserVO newBeeMallUserVO = new NewBeeMallUserVO();
                BeanUtil.copyProperties(currentUser, newBeeMallUserVO);
                httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, newBeeMallUserVO);
                return newBeeMallUserVO;
            }
        }

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
