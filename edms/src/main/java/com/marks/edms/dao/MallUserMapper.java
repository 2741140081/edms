package com.marks.edms.dao;

import com.marks.edms.entity.MallUser;
import com.marks.edms.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(MallUser record);

    /**
     * 保存一条记录
     * @param record
     * @return
     */
    int insertSelective(MallUser record);

    MallUser selectByPrimaryKey(Long userId);

    /**
     * 根据loginName查询记录
     * @param loginName
     * @return
     */
    MallUser selectByLoginName(String loginName);

    /**
     * 根据loginName和Password查询记录
     * @param loginName
     * @param password
     * @return
     */
    MallUser selectByLoginNameAndPasswd(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 更新一条记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MallUser record);

    int updateByPrimaryKey(MallUser record);

    List<MallUser> findMallUserList(PageQueryUtil pageUtil);

    int getTotalMallUsers(PageQueryUtil pageUtil);

    int lockUserBatch(@Param("ids") Long[] ids, @Param("lockStatus") int lockStatus);
}
