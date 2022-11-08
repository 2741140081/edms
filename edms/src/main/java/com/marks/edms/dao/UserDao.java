package com.marks.edms.dao;

import com.marks.edms.domain.SysUser;
import com.marks.edms.entity.User;

import java.util.List;

/**
 *
 */
public interface UserDao {
    /**
     * @param username
     * @return
     */
    public SysUser findByUserName(String username);

    /**
     * @return 所有User列表
     */
    public List<User> findAllUsers();

    /**
     * insert user
     * @param user
     * @return
     */
    public int insertUser(User user);

    /**
     * update User
     * @param user
     * @return
     */
    public int updateUser(User user);


    /**
     * delete User
     * @param id
     * @return
     */
    public int delUser(Integer id);
}
