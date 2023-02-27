package com.marks.edms.controller.admin;

import com.marks.edms.common.NewBeeMallCategoryLevelEnum;
import com.marks.edms.controller.common.ServiceResultEnum;
import com.marks.edms.entity.GoodsCategory;
import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.service.NewBeeMallCategoryService;
import com.marks.edms.service.NewBeeMallGoodsService;
import com.marks.edms.util.NewBeeMallUtils;
import com.marks.edms.util.Result;
import com.marks.edms.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class NewBeeMallGoodsController {

    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;

    @GetMapping("/goods")
    public String goodsPage(HttpServletRequest request) {
        request.setAttribute("path", "newbee_mall_goods");
        return "admin/newbee_mall_goods";
    }

    @GetMapping("/goods/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path","edit");
        //查询所有一级分类
        List<GoodsCategory> firstLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
            //查询一级分类列表中第一个实体的所有二级分类
            List<GoodsCategory> secondLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //查询二级分类列表中第一个实体的所有三级分类
                List<GoodsCategory> thirdLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());

                request.setAttribute("firstLevelCategories",firstLevelCategories);
                request.setAttribute("secondLevelCategories",secondLevelCategories);
                request.setAttribute("thirdLevelCategories",thirdLevelCategories);
                request.setAttribute("path", "goods-edit");
                return "admin/newbee_mall_goods_edit";
            }
        }


        return "error/error_5xx";

    }

    @RequestMapping(value = "/goods/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody NewBeeMallGoods goods) {
        if (!StringUtils.hasLength(goods.getGoodsName())
                || !StringUtils.hasLength(goods.getGoodsIntro())
                || !StringUtils.hasLength(goods.getTag())
                || Objects.isNull(goods.getOriginalPrice())
                || Objects.isNull(goods.getSellingPrice())
                || Objects.isNull(goods.getStockNum())
                || Objects.isNull(goods.getGoodsCategoryId())
                || Objects.isNull(goods.getGoodsSellStatus())
                || !StringUtils.hasLength(goods.getGoodsCoverImg())
                || !StringUtils.hasLength(goods.getGoodsDetailContent())) {
            return ResultGenerator.genFailResult("参数异常");
        }

        String result = newBeeMallGoodsService.saveNewBeeMallGoods(goods);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }

    }

}
