package com.marks.edms.controller.admin;

import com.marks.edms.service.NewBeeMallCategoryService;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import com.marks.edms.util.Result;
import com.marks.edms.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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


}
