package com.marks.edms.dao;

import com.marks.edms.entity.NewBeeMallGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewBeeMallGoodsMapper {

    int insertSelective(NewBeeMallGoods record);

    NewBeeMallGoods selectByPrimaryKey(Long goodsId);
}
