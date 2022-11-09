package com.marks.edms.dao;

import com.marks.edms.domain.SysUser;
import com.marks.edms.entity.User;
import com.marks.edms.util.PageQueryUtil;

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

    /**
     * 返回分页数据列表
     * @param pageUtil
     * @return
     */
    public List<User> findUsers(PageQueryUtil pageUtil);

    /**
     * 返回数据总条数
     * @param pageUtil
     * @return
     */
    public int getTotalUser(PageQueryUtil pageUtil);
}
