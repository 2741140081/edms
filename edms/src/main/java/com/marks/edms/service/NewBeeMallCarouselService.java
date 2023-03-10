package com.marks.edms.service;

import com.marks.edms.controller.vo.NewBeeMallIndexCarouselVO;
import com.marks.edms.entity.Carousel;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;

import java.util.List;

public interface NewBeeMallCarouselService {

    /**
     * 查询后台管理系统轮播图分页数据
     * @param pageQueryUtil
     * @return
     */
    PageResult getCarouselPage(PageQueryUtil pageQueryUtil);

    /**
     * 新增一条轮播图记录
     * @param carousel
     * @return
     */
    String saveCarousel(Carousel carousel);

    /**
     * 修改一条轮播图记录
     * @param carousel
     * @return
     */
    String updateCarousel(Carousel carousel);

    /**
     * 根据主键id查询轮播图记录
     * @param id
     * @return
     */
    Carousel getCarouselById(Integer id);

    /**
     * 批量删除轮播图记录
     * 修改id_deleted = 1
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);

    List<NewBeeMallIndexCarouselVO> getCarouselForIndex(int number);
}
