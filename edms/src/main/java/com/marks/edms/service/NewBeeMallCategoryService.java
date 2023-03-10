package com.marks.edms.service;

import com.marks.edms.controller.vo.NewBeeMallIndexCategoryVO;
import com.marks.edms.entity.GoodsCategory;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;

import java.util.List;

public interface NewBeeMallCategoryService {

    PageResult getCategorisePage(PageQueryUtil pageQueryUtil);

    String saveCategory(GoodsCategory goodsCategory);

    String updateGoodsCategory(GoodsCategory goodsCategory);

    GoodsCategory getGoodsCategoryById(Long id);

    Boolean deleteBatch(Integer[] ids);

    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, int categoryLevel);

    List<NewBeeMallIndexCategoryVO> getCategoriesForIndex();
}
