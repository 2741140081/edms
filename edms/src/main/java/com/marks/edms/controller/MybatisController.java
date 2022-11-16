package com.marks.edms.controller;

import com.marks.edms.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
public class MybatisController {
    @Autowired
    UserDao userDao;

    /**
     * @return 查询所有user
     */
    @GetMapping("users/mybatis/queryAll")
    public List<User> queryAll() {
        return userDao.findAllUsers();
    }

    @GetMapping("users/mybatis/insert")
    public Boolean insert(String name, String password) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return false;
        }
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userDao.insertUser(user) > 0;
    }

    @GetMapping("users/mybatis/update")
    public Boolean update(Integer id, String name, String password) {
        if (id == null || id < 0||StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return false;
        }
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        return userDao.updateUser(user) > 0;
    }

    @GetMapping("users/mybatis/delete")
    public Boolean delete(Integer id) {
        if (id == null || id < 0) {
            return false;
        }

        return userDao.delUser(id) > 0;
    }


}
