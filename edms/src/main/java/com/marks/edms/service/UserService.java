package com.marks.edms.service;

import com.marks.edms.dao.UserDao;
import com.marks.edms.entity.User;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public PageResult getUserPage(PageQueryUtil pageUtil) {
        List<User> users = userDao.findUsers(pageUtil);
        int totalUser = userDao.getTotalUser(pageUtil);
        PageResult pageResult = new PageResult(totalUser, pageUtil.getLimit(), pageUtil.getPage(), users);

        return pageResult;
    }
}
