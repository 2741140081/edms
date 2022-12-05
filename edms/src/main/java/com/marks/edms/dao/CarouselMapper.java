package com.marks.edms.dao;

import com.marks.edms.entity.Carousel;
import com.marks.edms.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarouselMapper {

    /**
     * 删除一条记录
     * @param carouselId
     * @return
     */
    int deleteByPrimaryKey(Integer carouselId);

    /**
     * 保存一条记录
     * @param carousel
     * @return
     */
    int insert(Carousel carousel);

    /**
     * 保存一条记录
     * @param carousel
     * @return
     */
    int insertSelective(Carousel carousel);

    /**
     * 查询记录
     * @param carouselId
     * @return
     */
    Carousel selectByPrimaryKey(Integer carouselId);

    /**
     * 修改记录
     * @param carousel
     * @return
     */
    int updateByPrimaryKeySelective(Carousel carousel);

    /**
     * 修改记录
     * @param carousel
     * @return
     */
    int updateByPrimaryKey(Carousel carousel);

    /**
     * 查询分页数据
     * @param pageQueryUtil
     * @return
     */
    List<Carousel> findCarouselList(PageQueryUtil pageQueryUtil);

    /**
     * 获取记录总数
     * @param pageQueryUtil
     * @return
     */
    int getTotalCarousels(PageQueryUtil pageQueryUtil);

    /**
     * 批量删除
     * @param Ids
     * @return
     */
    int deleteBath(Integer[] Ids);

    /**
     * 查询固定数量的记录
     * @param number
     * @return
     */
    List<Carousel> findCarouselByNum(@Param("number") int number);


}
