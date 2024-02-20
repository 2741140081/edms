package com.marks.edms.service.impl;

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
        registerUser.setOldPassword1(encryptedPassword);
        registerUser.setPasswordStatus('C');
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

            NewBeeMallUserVO newBeeMallUserVO = new NewBeeMallUserVO();
            BeanUtil.copyProperties(mallUser, newBeeMallUserVO);
            httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, newBeeMallUserVO);

            // 判断用户是否需要进行更新密码, 'R'表示需要 Reset Password, C表示改用户新建，由系统发送初始密码，需要用户更改密码
            if(mallUser.getPasswordStatus() == 'C' || mallUser.getPasswordStatus() == 'R') {
                return ServiceResultEnum.NEED_RESET_PASSWORD.getResult();
            }else {
                return ServiceResultEnum.SUCCESS.getResult();
            }


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

    /**
     * @param newPassword
     * @param httpSession
     * @return
     */
    @Override
    public String updateUserPassword(String oldPassword, String newPassword, HttpSession httpSession) {
        NewBeeMallUserVO userTemp = (NewBeeMallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        MallUser currentUser = userMapper.selectByPrimaryKey(userTemp.getUserId());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (currentUser != null && httpSession != null) {
            /**
             * 判断oldPassword是否与数据库密码一致
             */
            boolean matchFlag = encoder.matches(oldPassword, currentUser.getPasswordMd5());
            if (!matchFlag) {
                return ServiceResultEnum.LOGIN_PASSWORD_ERROR.getResult();
            }
            /**
             * 加密newPassword，
             * 判断newPassword是否与数据库中之前3次保存的密码一致，如果一致，则返回ERROR
             * 保存到数据库中
             */
            MallUser registerUser = new MallUser();
            String encryptedPassword = encoder.encode(newPassword);
            String oldPassword1 = currentUser.getOldPassword1();
            String oldPassword2 = currentUser.getOldPassword2();
            String oldPassword3 = currentUser.getOldPassword3();

            boolean matchFlag1 = encoder.matches(newPassword, oldPassword1);
            boolean matchFlag2 = encoder.matches(newPassword, oldPassword2);
            boolean matchFlag3 = encoder.matches(newPassword, oldPassword3);

            if (matchFlag1 || matchFlag2 || matchFlag3) {
                return ServiceResultEnum.NEW_PASSWORD_REPEAT_ERROR.getResult();
            }

            currentUser.setPasswordUpdateTime(new Date());
            currentUser.setPasswordStatus('U');
            currentUser.setPasswordMd5(encryptedPassword);
            currentUser.setOldPassword1(encryptedPassword);
            if (oldPassword1 != null && oldPassword1 != "") {
                currentUser.setOldPassword2(oldPassword1);
            }

            if (oldPassword2 != null && oldPassword2 != "") {
                currentUser.setOldPassword3(oldPassword2);
            }

            if (userMapper.updateByPrimaryKeySelective(currentUser) > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            }


        }
        return ServiceResultEnum.ERROR.getResult();
    }

    /**
     * @param pageUtil
     * @return
     */
    @Override
    public PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil) {
        List<MallUser> mallUsers = userMapper.findMallUserList(pageUtil);
        int total = userMapper.getTotalMallUsers(pageUtil);
        PageResult pageResult = new PageResult(pageUtil.getPage(), total, pageUtil.getLimit(), mallUsers);
        return pageResult;
    }
}
