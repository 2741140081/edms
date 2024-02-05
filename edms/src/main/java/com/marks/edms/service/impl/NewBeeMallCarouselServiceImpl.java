package com.marks.edms.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.marks.edms.common.ServiceResultEnum;
import com.marks.edms.controller.vo.NewBeeMallIndexCarouselVO;
import com.marks.edms.dao.CarouselMapper;
import com.marks.edms.entity.Carousel;
import com.marks.edms.service.NewBeeMallCarouselService;
import com.marks.edms.util.BeanUtil;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewBeeMallCarouselServiceImpl implements NewBeeMallCarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public PageResult getCarouselPage(PageQueryUtil pageQueryUtil) {
        List<Carousel> carousels = carouselMapper.findCarouselList(pageQueryUtil);
        int totalCarousels = carouselMapper.getTotalCarousels(pageQueryUtil);
        PageResult pageResult = new PageResult(totalCarousels, pageQueryUtil.getLimit(), pageQueryUtil.getPage(),carousels);
        return pageResult;
    }

    @Override
    public String saveCarousel(Carousel carousel) {
        if (carouselMapper.insertSelective(carousel) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.ERROR.getResult();
    }

    @Override
    public String updateCarousel(Carousel carousel) {
        Carousel carouselTemp = carouselMapper.selectByPrimaryKey(carousel.getCarouselId());
        if (carouselTemp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        carouselTemp.setCarouselRank(carousel.getCarouselRank());
        carouselTemp.setRedirectUrl(carousel.getRedirectUrl());
        carouselTemp.setCarouselUrl(carousel.getCarouselUrl());
        carouselTemp.setUpdateTime(new Date());
        if (carouselMapper.updateByPrimaryKey(carouselTemp) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Carousel getCarouselById(Integer id) {
        Carousel carousel = carouselMapper.selectByPrimaryKey(id);
        return carousel;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        int deleteBathNum = carouselMapper.deleteBath(ids);
        return deleteBathNum > 0;
    }

    /**
     * @param number
     * @return
     */
    @Override
    public List<NewBeeMallIndexCarouselVO> getCarouselForIndex(int number) {
        ArrayList<NewBeeMallIndexCarouselVO> newBeeMallIndexCarouselVOS = new ArrayList<>(number);
        List<Carousel> carousels = carouselMapper.findCarouselByNum(number);
        if (!CollectionUtils.isEmpty(carousels)) {
            newBeeMallIndexCarouselVOS = (ArrayList<NewBeeMallIndexCarouselVO>) BeanUtil.copyList(carousels, NewBeeMallIndexCarouselVO.class);

        }
        return newBeeMallIndexCarouselVOS;
    }
}
