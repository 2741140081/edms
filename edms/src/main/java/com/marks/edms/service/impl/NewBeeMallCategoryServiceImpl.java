package com.marks.edms.service.impl;

import com.marks.edms.dao.GoodsCategoryMapper;
import com.marks.edms.entity.GoodsCategory;
import com.marks.edms.service.NewBeeMallCategoryService;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewBeeMallCategoryServiceImpl implements NewBeeMallCategoryService {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public PageResult getCategorisePage(PageQueryUtil pageQueryUtil) {
        return null;
    }

    @Override
    public String saveCategory(GoodsCategory goodsCategory) {
        return null;
    }

    @Override
    public String updateGoodsCategory(GoodsCategory goodsCategory) {
        return null;
    }

    @Override
    public GoodsCategory getGoodsCategoryById(Long id) {
        return null;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return null;
    }

    @Override
    public List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel) {
        return null;
    }
}
