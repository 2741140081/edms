package com.marks.edms.dao;

import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewBeeMallGoodsMapper {

    int insertSelective(NewBeeMallGoods record);

    NewBeeMallGoods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(NewBeeMallGoods goods);

    List<NewBeeMallGoods> findNewBeeMallGoodsList(PageQueryUtil pageQueryUtil);

    int getTotalNewBeeMallGoods(PageQueryUtil pageQueryUtil);

    int batchUpdateSellStatus(@Param("goodsId") Long[] goodsIds[], @Param("sellStatus") int sellStatus);
}
