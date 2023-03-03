package com.marks.edms.service;

import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.stereotype.Service;


public interface NewBeeMallGoodsService {
    String saveNewBeeMallGoods(NewBeeMallGoods goods);

    NewBeeMallGoods getNewBeeMallGoodsById(Long goodsId);

    String updateNewBeeMallGoods(NewBeeMallGoods goods);

    PageResult getNewBeeMallGoodsPage(PageQueryUtil pageQueryUtil);

    Boolean batchUpdateSellStatus(Long[] goodIds, int sellStatus);
}
