package com.marks.edms.dao;

import com.marks.edms.entity.GoodsCategory;
import com.marks.edms.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Long categoryId);

    int insert(GoodsCategory goodsCategory);

    int insertSelective(GoodsCategory goodsCategory);

    GoodsCategory selectByPrimaryKey(Long categoryId);

    /**
     * 根据分类等级和分类名称查询一条分类记录
     * @param categoryLevel
     * @param categoryName
     * @return
     */
    GoodsCategory selectByLevelAndName(@Param("categoryLevel") Byte categoryLevel, @Param("categoryName") String categoryName);

    int updateByPrimaryKeySelective(GoodsCategory goodsCategory);

    int updateByPrimaryKey(GoodsCategory goodsCategory);

    /**
     * 查询分页数据
     * @param pageQueryUtil
     * @return
     */
    List<GoodsCategory> findGoodsCategoryList(PageQueryUtil pageQueryUtil);

    /**
     * 获取记录总数
     * @param pageQueryUtil
     * @return
     */
    int getTotalGoodsCategories(PageQueryUtil pageQueryUtil);

    /**
     * 批量删除
     * @param Ids
     * @return
     */
    int deleteBath(Integer[] Ids);

    /**
     * 根据父类的id、分类等级和数量查询分类列表
     * @param parentIds
     * @param categoryLevel
     * @param number
     * @return
     */
    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(@Param("parentIds") List<Long> parentIds,
                                                           @Param("categoryLevel") int categoryLevel, @Param("number") int number);
}
