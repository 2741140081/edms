package com.marks.edms.dao;

import com.marks.edms.entity.IndexConfig;
import com.marks.edms.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexConfigMapper {
    /**
     * 删除一条数据
     * @param configId
     * @return
     */
    int deleteByPrimaryKey(Long configId);

    /**
     * 保存一条新数据
     * @param record
     * @return
     */
    int insert(IndexConfig record);

    /**
     * 保存一条新数据
     * @param record
     * @return
     */
    int insertSelective(IndexConfig record);

    /**
     * 根据主键查询记录
     * @param configId
     * @return
     */
    IndexConfig selectByPrimaryKey(Long configId);

    /**
     * 根据商品类型和物品id查询记录
     * @param configType
     * @param goodsId
     * @return
     */
    IndexConfig selectByTypeAndGoodsId(@Param("configType") int configType, @Param("goodsId") Long goodsId);

    /**
     * 修改记录
     * @param reocrd
     * @return
     */
    int updateByPrimaryKeySelective(IndexConfig reocrd);

    /**
     * 修改记录
     * @param reocrd
     * @return
     */
    int updateByPrimaryKey(IndexConfig reocrd);

    /**
     * 返回分页列表数据
     * @param pageUtil
     * @return
     */
    List<IndexConfig> findIndexConfigList(PageQueryUtil pageUtil);

    /**
     * 获取分页总数
     * @param pageUtil
     * @return
     */
    int getTotalIndexConfigs(PageQueryUtil pageUtil);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBatch(Long[] ids);

    /**
     *
     * @param configType
     * @param number
     * @return
     */
    List<IndexConfig> findIndexConfigsByTypeAndNum(@Param("configType") int configType, @Param("number") int number);
}
