package com.marks.edms.dao;

import com.marks.edms.entity.Carousel;
import com.marks.edms.util.PageQueryUtil;

import java.util.List;

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

    List<Carousel> findCarouselList(PageQueryUtil pageQueryUtil);


}
