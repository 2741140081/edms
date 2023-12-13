package com.marks.edms.controller.mall;

import com.marks.edms.common.Constants;
import com.marks.edms.common.NewBeeMallException;
import com.marks.edms.common.IndexConfigTypeEnum;
import com.marks.edms.controller.vo.NewBeeMallIndexCarouselVO;
import com.marks.edms.controller.vo.NewBeeMallIndexCategoryVO;
import com.marks.edms.controller.vo.NewBeeMallIndexConfigGoodsVO;
import com.marks.edms.service.NewBeeMallCarouselService;
import com.marks.edms.service.NewBeeMallCategoryService;
import com.marks.edms.service.NewBeeMallIndexConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private NewBeeMallCarouselService newBeeMallCarouselService;

    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

    @Resource
    private NewBeeMallIndexConfigService newBeeMallIndexConfigService;

    @GetMapping({"/index", "/", "/index.html"})
    public String indexPage(HttpServletRequest request) {
        List<NewBeeMallIndexCategoryVO> categories = newBeeMallCategoryService.getCategoriesForIndex();
        if (CollectionUtils.isEmpty(categories)) {
            NewBeeMallException.fail("分类数据不完善");
        }
        request.setAttribute("categories", categories); //分类数据
        List<NewBeeMallIndexCarouselVO> carousels = newBeeMallCarouselService.getCarouselForIndex(Constants.INDEX_CAROUSEL_NUMBER);
        request.setAttribute("carousels", carousels); //轮播图


        List<NewBeeMallIndexConfigGoodsVO> newGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_GOODS_NEW_NUMBER);
        request.setAttribute("newGoodses", newGoodses); //新品推荐
        List<NewBeeMallIndexConfigGoodsVO> hotGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
        request.setAttribute("hotGoodses", hotGoodses); //热销推荐
        List<NewBeeMallIndexConfigGoodsVO> recommendGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUMBER);
        request.setAttribute("recommendGoodses", hotGoodses); //推荐商品
        return "mall/index";
    }

    @GetMapping("/mall/editor")
    public String editor() {
        return "mall/markdownTest";
    }

    @PostMapping("/mall/saveMarkdown")
    @ResponseBody
    public String save(@RequestParam("content") String content) {
        // 处理Markdown内容保存的逻辑
        return "保存成功！";
    }
}
