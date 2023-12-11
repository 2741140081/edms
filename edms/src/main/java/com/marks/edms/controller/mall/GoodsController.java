package com.marks.edms.controller.mall;


import com.marks.edms.common.Constants;
import com.marks.edms.service.NewBeeMallCategoryService;
import com.marks.edms.service.NewBeeMallGoodsService;
import com.marks.edms.util.PageQueryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
//            categoryService
        }

        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);

//        goodsService.searchNewBeeMallGoods()
        return "/mall/search";
    }
}
