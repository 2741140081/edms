package com.marks.edms.service.impl;

import com.marks.edms.common.ServiceResultEnum;
import com.marks.edms.controller.vo.NewBeeMallIndexConfigGoodsVO;
import com.marks.edms.dao.IndexConfigMapper;
import com.marks.edms.dao.NewBeeMallGoodsMapper;
import com.marks.edms.entity.IndexConfig;
import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.service.NewBeeMallIndexConfigService;
import com.marks.edms.util.BeanUtil;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class NewBeeMallIndexConfigServiceImpl implements NewBeeMallIndexConfigService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    @Override
    public PageResult getConfigsPage(PageQueryUtil pageUtil) {
        List<IndexConfig> indexConfigList = indexConfigMapper.findIndexConfigList(pageUtil);
        int totalIndexConfigs = indexConfigMapper.getTotalIndexConfigs(pageUtil);
        PageResult result = new PageResult(totalIndexConfigs, pageUtil.getLimit(), pageUtil.getPage(), indexConfigList);
        return result;
    }

    /**
     * @param indexConfig
     * @return
     */
    @Override
    public String saveIndexConfig(IndexConfig indexConfig) {
        NewBeeMallGoods newBeeMallGoods = goodsMapper.selectByPrimaryKey(indexConfig.getGoodsId());
        if (newBeeMallGoods == null) {
            return ServiceResultEnum.GOODS_NOT_EXIST.getResult();
        }
        IndexConfig temp = indexConfigMapper.selectByTypeAndGoodsId(indexConfig.getConfigType(), indexConfig.getGoodsId());
        if (temp != null) {
            return ServiceResultEnum.SAME_INDEX_CONFIG_EXIST.getResult();
        }

        if (indexConfigMapper.insertSelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    /**
     * @param indexConfig
     * @return
     */
    @Override
    public String updateIndexConfig(IndexConfig indexConfig) {
        NewBeeMallGoods newBeeMallGoods = goodsMapper.selectByPrimaryKey(indexConfig.getGoodsId());
        if (newBeeMallGoods == null) {
            return ServiceResultEnum.GOODS_NOT_EXIST.getResult();
        }
        IndexConfig indexConfigTemp = indexConfigMapper.selectByPrimaryKey(indexConfig.getConfigId());
        if (indexConfigTemp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        IndexConfig indexConfigTemp2 = indexConfigMapper.selectByTypeAndGoodsId(indexConfig.getConfigType(), indexConfig.getGoodsId());
        if (indexConfigTemp2 != null && !indexConfigTemp2.getConfigId().equals(indexConfigTemp.getConfigId())) {
            //同名且不同id不能修改
            return ServiceResultEnum.SAME_INDEX_CONFIG_EXIST.getResult();
        }
        Date date = new Date();
        indexConfigTemp.setUpdateTime(date);
        if (indexConfigMapper.updateByPrimaryKeySelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public IndexConfig getIndexConfigById(Long id) {
        return indexConfigMapper.selectByPrimaryKey(id);
    }

    /**
     * 返回固定数量的首页配置商品对象(首页调用)
     *
     * @param configType
     * @param number
     * @return
     */
    @Override
    public List<NewBeeMallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number) {
        List<NewBeeMallIndexConfigGoodsVO> newBeeMallIndexConfigGoodsVOS = new ArrayList<>(number);
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigsByTypeAndNum(configType, number);
        if (!CollectionUtils.isEmpty(indexConfigs)) {
            //取出所有的goodsId
            List<Long> goodsIds = indexConfigs.stream().map(IndexConfig::getGoodsId).collect(Collectors.toList());
            List<NewBeeMallGoods> newBeeMallGoods = goodsMapper.selectByPrimaryKeys(goodsIds);
            newBeeMallIndexConfigGoodsVOS = BeanUtil.copyList(newBeeMallGoods, NewBeeMallIndexConfigGoodsVO.class);
            for (NewBeeMallIndexConfigGoodsVO newBeeMallIndexConfigGoodsVO : newBeeMallIndexConfigGoodsVOS) {
                String goodsName = newBeeMallIndexConfigGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallIndexConfigGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 30) {
                    goodsName = goodsName.substring(0, 30) + "...";
                    newBeeMallIndexConfigGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 22) {
                    goodsIntro = goodsIntro.substring(0, 22) + "...";
                    newBeeMallIndexConfigGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        return newBeeMallIndexConfigGoodsVOS;
    }

    /**
     * @param ids
     * @return
     */
    @Override
    public Boolean deleteBatch(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return indexConfigMapper.deleteBatch(ids) > 0;
    }
}
