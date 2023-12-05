package com.marks.edms.dao;

import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.entity.StockNumDTO;
import com.marks.edms.util.PageQueryUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewBeeMallGoodsMapperTest {
    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;

    @Transactional
    @Test
    void insertSelective() {
        NewBeeMallGoods goods = new NewBeeMallGoods();
        goods.setGoodsName("Goods Name Test Update");
        goods.setGoodsIntro("Goods Intro Test Update");
        goods.setGoodsCategoryId(51L);
        goods.setGoodsCoverImg("/admin/dist/img/number Test Update-img.png");
        goods.setGoodsCarousel("/admin/dist/img/number Test Update-img.png");
        goods.setGoodsDetailContent("<p>商品介绍加载中...</p>");
        goods.setOriginalPrice(1599);
        goods.setSellingPrice(1299);
        goods.setStockNum(1000);

        int insertNumber = goodsMapper.insertSelective(goods);
        Boolean flag = (insertNumber>0);
        Assertions.assertEquals(true, flag,"Insert数据错误，数据条数为"+ insertNumber);
    }

    @Transactional
    @Test
    void selectByPrimaryKey() {
        Long goodsId = 10907L;
        NewBeeMallGoods goods = goodsMapper.selectByPrimaryKey(goodsId);
        Boolean flag = (goods != null);
        Assertions.assertEquals(true, flag,"Select数据错误，数据条数为"+ goods);
    }

    @Transactional
    @Test
    void updateByPrimaryKeySelective() {
        NewBeeMallGoods goods = new NewBeeMallGoods();
        goods.setGoodsName("Goods Name Test Update");
        goods.setGoodsIntro("Goods Intro Test Update");
        goods.setGoodsCategoryId(51L);
        goods.setGoodsCoverImg("/admin/dist/img/number Test Update-img.png");
        goods.setGoodsCarousel("/admin/dist/img/number Test Update-img.png");
        goods.setGoodsDetailContent("<p>商品介绍加载中...</p>");
        goods.setOriginalPrice(1599);
        goods.setSellingPrice(1299);
        goods.setStockNum(1000);
        goods.setGoodsId(10907L);

        int updateNumber = goodsMapper.updateByPrimaryKeySelective(goods);
        Boolean flag = (updateNumber>0);
        Assertions.assertEquals(true, flag,"Update数据错误，数据条数为"+ updateNumber);

    }

    @Transactional
    @Test
    void findNewBeeMallGoodsList() {
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("limit",10);
        params.put("start",0);
        params.put("goodsName","手机");
        params.put("goodsSellStatus",0);
        params.put("startTime",null);
        params.put("endTime",null);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        List<NewBeeMallGoods> newBeeMallGoodsList = goodsMapper.findNewBeeMallGoodsList(pageUtil);

        Boolean flag = (newBeeMallGoodsList.size()>1);
        Assertions.assertEquals(true, flag,"查询数据错误，数据条数为"+ newBeeMallGoodsList.size());

    }

    @Transactional
    @Test
    void getTotalNewBeeMallGoods() {
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("limit",10);
        params.put("start",0);
        params.put("goodsName","手机");
        params.put("goodsSellStatus",0);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        int totalNewBeeMallGoods = goodsMapper.getTotalNewBeeMallGoods(pageUtil);

        Boolean flag = (totalNewBeeMallGoods>1);
        Assertions.assertEquals(true, flag,"查询数据错误，数据条数为"+ totalNewBeeMallGoods);
    }

    @Transactional
    @Test
    void batchUpdateSellStatus() {
        Long[] goodsIds = new Long[10];
        for (int i = 0; i < 10; i++) {
            goodsIds[i] = 10101L+i;
        }
        int batchUpdateSellStatusNumber = goodsMapper.batchUpdateSellStatus(goodsIds, 1);
        Assertions.assertEquals(10, batchUpdateSellStatusNumber, "实际值为:" + batchUpdateSellStatusNumber);

    }

    @Transactional
    @Test
    void selectByPrimaryKeys() {
        List<Long> goodsIds = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            goodsIds.add(10101L+i);
        }
        List<NewBeeMallGoods> newBeeMallGoods = goodsMapper.selectByPrimaryKeys(goodsIds);
        Assertions.assertEquals(10, newBeeMallGoods.size(), "实际值为:" + newBeeMallGoods.size());

    }

    @Transactional
    @Test
    void findNewBeeMallGoodsListBySearch() {
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("limit",5);
        params.put("start",0);
        params.put("keyword", "手机");
        params.put("goodsCategoryId", 51);
        params.put("goodsSellStatus", 0);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        List<NewBeeMallGoods> totalNewBeeMallGoodsBySearch = goodsMapper.findNewBeeMallGoodsListBySearch(pageUtil);
        Assertions.assertEquals(5, totalNewBeeMallGoodsBySearch.size(), "实际值为:" + totalNewBeeMallGoodsBySearch.size());
    }

    @Transactional
    @Test
    void getTotalNewBeeMallGoodsBySearch() {
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("limit",10);
        params.put("start",0);
        params.put("keyword", "手机");
        params.put("goodsCategoryId", 51);
        params.put("goodsSellStatus", 0);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        int totalNewBeeMallGoodsBySearch = goodsMapper.getTotalNewBeeMallGoodsBySearch(pageUtil);
        Assertions.assertEquals(67, totalNewBeeMallGoodsBySearch, "实际值为:" + totalNewBeeMallGoodsBySearch);
    }

    @Transactional
    @Test
    void batchInsert() {
        List<NewBeeMallGoods> newBeeMallGoodsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewBeeMallGoods goods = new NewBeeMallGoods();
            goods.setGoodsName("Goods Name" + i);
            goods.setGoodsIntro("Goods Intro" + i);
            goods.setGoodsCategoryId(51L);
            goods.setGoodsCoverImg("/admin/dist/img/number-" + i +"-img.png");
            goods.setGoodsCarousel("/admin/dist/img/number-" + i +"-img.png");
            goods.setGoodsDetailContent("<p>商品介绍加载中...</p>");
            goods.setOriginalPrice(1599 + i);
            goods.setSellingPrice(1299 - i);
            goods.setStockNum(1000 + 100 * i);
            newBeeMallGoodsList.add(goods);
        }
        int batchInsertNumber = goodsMapper.batchInsert(newBeeMallGoodsList);
        Assertions.assertEquals(true,batchInsertNumber==newBeeMallGoodsList.size(),"需要newBeeMallGoodsList数据number:"+ newBeeMallGoodsList.size() +", 实际更新batchInsertNumber数据number:"+ batchInsertNumber);
    }

    @Transactional
    @Test
    void updateStockNum() {
        List<StockNumDTO> stockNumDTOS = new ArrayList<>();
        stockNumDTOS.add(new StockNumDTO(10886L,10));
        stockNumDTOS.add(new StockNumDTO(10887L,5));
        stockNumDTOS.add(new StockNumDTO(10888L,100));
        int updateStockNum = goodsMapper.updateStockNum(stockNumDTOS);
        Assertions.assertEquals(true,updateStockNum== 1,"需要updateStockNum的数据number:"+ stockNumDTOS.size() +", 实际更新updateStockNum库存数据number:"+ updateStockNum);
    }

    @Transactional
    @Test
    void recoverStockNum() {
        List<StockNumDTO> stockNumDTOS = new ArrayList<>();
        stockNumDTOS.add(new StockNumDTO(10886L,10));
        stockNumDTOS.add(new StockNumDTO(10887L,5));
        stockNumDTOS.add(new StockNumDTO(10888L,100));
        int recoverStockNum = goodsMapper.recoverStockNum(stockNumDTOS);
        Assertions.assertEquals(true,recoverStockNum==1,"需要recover的数据number:"+ stockNumDTOS.size() +", 实际更新recover库存数据number:"+ recoverStockNum);
    }
}