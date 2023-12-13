package com.marks.edms.controller.admin;

import com.marks.edms.common.NewBeeMallCategoryLevelEnum;
import com.marks.edms.common.ServiceResultEnum;
import com.marks.edms.entity.GoodsCategory;
import com.marks.edms.service.NewBeeMallCategoryService;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import com.marks.edms.util.Result;
import com.marks.edms.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class NewBeeMallGoodsCategoryController {
    @Resource
    NewBeeMallCategoryService newBeeMallCategoryService;
    @GetMapping("/categories")
    public String category(HttpServletRequest request, @RequestParam("parentId") Long parentId, @RequestParam("categoryLevel") Byte categoryLevel,
                           @RequestParam("backParentId") Long backParentId){
        if (categoryLevel == null || categoryLevel < 1 || categoryLevel > 3) {
            return "error/error_5xx";
        }

        request.setAttribute("path","newbee_mall_category");
        request.setAttribute("parentId",parentId);
        request.setAttribute("backParentId",backParentId);
        request.setAttribute("categoryLevel",categoryLevel);
        return "admin/newbee_mall_category";
    }

    @RequestMapping(value = "/categories/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常");
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        PageResult result = newBeeMallCategoryService.getCategorisePage(pageQueryUtil);
        return ResultGenerator.genSuccessResult(result);
    }

    @RequestMapping(value = "/categories/listForSelect", method = RequestMethod.GET)
    @ResponseBody
    public Result listForSelect(@RequestParam("categoryId") Long categoryId) {
        if (categoryId == null && categoryId < 1) {
            return ResultGenerator.genFailResult("缺少参数");
        }
        GoodsCategory category = newBeeMallCategoryService.getGoodsCategoryById(categoryId);
        //既不是一级分类也不是二级分类则不返回数据
        if (category == null && category.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ResultGenerator.genFailResult("参数异常");
        }
        HashMap<Object, Object> categoryResult = new HashMap<>(4);
        if (category.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel()) {
            //如果是一级分类，则返回当前一级分类下所有的二级分类，以及二级分类列表中第一条数据下的所有三级分类列表
            //查询一级列表中第一个实体的所有二级分类
            List<GoodsCategory> secondLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(categoryId), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                //查询二级分类列表中第一个实体的所有三级分类
                List<GoodsCategory> thirdLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());
                categoryResult.put("secondLevelCategories", secondLevelCategories);
                categoryResult.put("thirdLevelCategories", thirdLevelCategories);
            }
        }

        if (category.getCategoryLevel() == NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel()) {
            //如果是二级分类，则返回当前二级分类下所有的三级分类列表
            List<GoodsCategory> thirdLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(categoryId), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());
            categoryResult.put("thirdLevelCategories", thirdLevelCategories);
        }

        return ResultGenerator.genSuccessResult(categoryResult);
    }

    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody GoodsCategory goodsCategory) {
        if (!StringUtils.hasLength(goodsCategory.getCategoryName()) || Objects.isNull(goodsCategory.getCategoryLevel()) ||
                Objects.isNull(goodsCategory.getParentId()) || Objects.isNull(goodsCategory.getCategoryRank())) {
            return ResultGenerator.genFailResult("参数异常");
        }
        String result = newBeeMallCategoryService.saveCategory(goodsCategory);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }
    }


}
