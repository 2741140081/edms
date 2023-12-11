package com.marks.edms.controller.mall;


import com.marks.edms.common.Constants;
import com.marks.edms.controller.admin.NewBeeMallException;
import com.marks.edms.controller.vo.NewBeeMallGoodsDetailVO;
import com.marks.edms.controller.vo.SearchPageCategoryVO;
import com.marks.edms.entity.NewBeeMallGoods;
import com.marks.edms.service.NewBeeMallCategoryService;
import com.marks.edms.service.NewBeeMallGoodsService;
import com.marks.edms.util.BeanUtil;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class GoodsController {

    @Resource
    private NewBeeMallGoodsService goodsService;
    @Resource
    private NewBeeMallCategoryService categoryService;


    @GetMapping({"/search", "search.html"})
    public String searchPage(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        if (ObjectUtils.isEmpty(params.get("page"))) {
            params.put("page", 1);
        }
        params.put("limit", Constants.GOODS_SEARCH_PAGE_LIMIT);
        //封装分类数据
        if (params.containsKey("goodsCategoryId") && !ObjectUtils.isEmpty(params.get("goodsCategoryId")+"")) {
            Long categoryId = (Long) params.get("goodsCategoryId");
            SearchPageCategoryVO searchResultVO = categoryService.getCategoriesForSearch(categoryId);
            if (searchResultVO != null) {
                request.setAttribute("goodsCategoryId", categoryId);
                request.setAttribute("SearchPageCategoryVO", searchResultVO);
            }
        }

        if (params.containsKey("orderBy") && !ObjectUtils.isEmpty(params.get("orderBy")+"")) {
            request.setAttribute("orderBy", params.get("orderBy"));
        }
        String keyword = "";
        if (params.containsKey("keyword") && !ObjectUtils.isEmpty((params.get("keyword")+"").trim())) {
            keyword = params.get("keyword") + "";
        }
        request.setAttribute("keyword", params.get("keyword"));
        params.put("keyword", keyword);
        params.put("goodsSellStatus", Constants.SELL_STATUS_UP);
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);

        PageResult result = goodsService.searchNewBeeMallGoods(pageQueryUtil);
        request.setAttribute("PageResult", result);
        return "/mall/search";
    }

    @GetMapping("/goods/detail/{goodsId}")
    public String detailPage(@PathVariable("goodsId") Long goodsId, HttpServletRequest request) {
        if (goodsId < 1) {
            NewBeeMallException.fail("参数异常");
        }
        NewBeeMallGoods newBeeMallGoods = goodsService.getNewBeeMallGoodsById(goodsId);
        if (newBeeMallGoods == null) {
            //商品不存在
            return "error/error_400";
        }
        if (newBeeMallGoods.getGoodsSellStatus().equals(Constants.SELL_STATUS_DOWN)) {
            NewBeeMallException.fail("商品已下架");
        }
        NewBeeMallGoodsDetailVO detailVO = new NewBeeMallGoodsDetailVO();
        BeanUtil.copyProperties(newBeeMallGoods, detailVO);
        detailVO.setGoodsCarouselList(newBeeMallGoods.getGoodsCarousel().split(","));
        request.setAttribute("goodsDetail", detailVO);

        return "/mall/detail";
    }
}
