package com.marks.edms.service;

import com.marks.edms.entity.NewBeeMallGoods;
import org.springframework.stereotype.Service;


public interface NewBeeMallGoodsService {
    String saveNewBeeMallGoods(NewBeeMallGoods goods);

    NewBeeMallGoods getNewBeeMallGoodsById(Long goodsId);

    String updateNewBeeMallGoods(NewBeeMallGoods goods);
}
