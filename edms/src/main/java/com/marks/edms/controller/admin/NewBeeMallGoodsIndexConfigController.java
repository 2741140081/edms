package com.marks.edms.controller.admin;

import com.marks.edms.controller.common.IndexConfigTypeEnum;
import com.marks.edms.controller.common.ServiceResultEnum;
import com.marks.edms.entity.IndexConfig;
import com.marks.edms.service.NewBeeMallIndexConfigService;
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
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class NewBeeMallGoodsIndexConfigController {

    @Resource
    private NewBeeMallIndexConfigService newBeeMallIndexConfigService;

    @GetMapping("/indexConfigs")
    public String indexConfigsPage(HttpServletRequest request, @RequestParam("configType") int configType) {
        IndexConfigTypeEnum indexConfigTypeEnum = IndexConfigTypeEnum.getIndexConfigTypeEnumByType(configType);
        if (indexConfigTypeEnum.equals(IndexConfigTypeEnum.DEFAULT)) {
            NewBeeMallException.fail("参数异常");
        }

        request.setAttribute("path", indexConfigTypeEnum.getName());
        request.setAttribute("configType", configType);
        return "admin/newbee_mall_index_config";
    }

    @GetMapping("/indexConfigs/edit")
    public String edit(HttpServletRequest request) {

        request.setAttribute("path", "index-config-edit");
        return "admin/newbee_mall_index_config_edit";
    }

    @RequestMapping(value = "/indexConfigs/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常");
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        PageResult configsPage = newBeeMallIndexConfigService.getConfigsPage(pageQueryUtil);
        return ResultGenerator.genSuccessResult(configsPage);
    }

    @RequestMapping(value = "/indexConfigs/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody IndexConfig indexConfig) {
        if (Objects.isNull(indexConfig.getConfigType())
                ||Objects.isNull(indexConfig.getConfigName())
                ||Objects.isNull(indexConfig.getConfigRank())
                ||Objects.isNull(indexConfig.getGoodsId())) {
            return ResultGenerator.genFailResult("参数异常! ");
        }
        String result = newBeeMallIndexConfigService.saveIndexConfig(indexConfig);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }
    }

    @RequestMapping(value = "/indexConfigs/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestBody IndexConfig indexConfig) {
        if (Objects.isNull(indexConfig.getConfigType())
                ||Objects.isNull(indexConfig.getConfigName())
                ||Objects.isNull(indexConfig.getConfigRank())
                ||Objects.isNull(indexConfig.getConfigId())
                ||Objects.isNull(indexConfig.getGoodsId())) {
            return ResultGenerator.genFailResult("参数异常! ");
        }
        String result = newBeeMallIndexConfigService.updateIndexConfig(indexConfig);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult(result);
        }
    }

    @RequestMapping(value = "/indexConfigs/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        Boolean flag = newBeeMallIndexConfigService.deleteBatch(ids);
        if (flag) {
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
