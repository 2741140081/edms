package com.marks.edms.service.impl;

import com.marks.edms.controller.common.ServiceResultEnum;
import com.marks.edms.dao.GoodsCategoryMapper;
import com.marks.edms.entity.GoodsCategory;
import com.marks.edms.service.NewBeeMallCategoryService;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewBeeMallCategoryServiceImpl implements NewBeeMallCategoryService {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public PageResult getCategorisePage(PageQueryUtil pageQueryUtil) {
        List<GoodsCategory> goodsCategoryList = goodsCategoryMapper.findGoodsCategoryList(pageQueryUtil);
        int totalGoodsCategories = goodsCategoryMapper.getTotalGoodsCategories(pageQueryUtil);
        PageResult pageResult = new PageResult(totalGoodsCategories, pageQueryUtil.getLimit(), pageQueryUtil.getPage(), goodsCategoryList);
        return pageResult;
    }

    @Override
    public String saveCategory(GoodsCategory goodsCategory) {
        GoodsCategory temp = goodsCategoryMapper.selectByLevelAndName(goodsCategory.getCategoryLevel(), goodsCategory.getCategoryName());
        if (temp == null) {
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }

        if (goodsCategoryMapper.insertSelective(goodsCategory) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }

        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateGoodsCategory(GoodsCategory goodsCategory) {
        GoodsCategory goodsCategoryTemp = goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getCategoryId());
        if (goodsCategoryTemp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        GoodsCategory temp2 = goodsCategoryMapper.selectByLevelAndName(goodsCategory.getCategoryLevel(), goodsCategory.getCategoryName());
        if (temp2 != null && !temp2.getCategoryId().equals(goodsCategory.getCategoryId())) {
            //同名且不同id不能修改
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        Date date = new Date();
        goodsCategoryTemp.setUpdateTime(date);
        if (goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public GoodsCategory getGoodsCategoryById(Long id) {
        return goodsCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return goodsCategoryMapper.deleteBath(ids) >0;
    }

    @Override
    public List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel) {
        return goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(parentIds, categoryLevel, 0);
    }
}
