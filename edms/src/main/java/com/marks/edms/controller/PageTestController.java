package com.marks.edms.controller;

import com.marks.edms.service.UserService;
import com.marks.edms.util.PageQueryUtil;
import com.marks.edms.util.PageResult;
import com.marks.edms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class PageTestController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        Result result = new Result();
        if (StringUtils.isEmpty((String) params.get("page")) || StringUtils.isEmpty((String) params.get("limit"))) {
            result.setResultCode(500);
            result.setMessage("参数异常！");
            return result;
        }
        //封装查询参数
        PageQueryUtil queryParamsList = new PageQueryUtil(params);
        //查询并封装分页结果
        PageResult userPage = userService.getUserPage(queryParamsList);
        //返回成功码
        result.setResultCode(200);
        result.setMessage("查询成功");
        //返回分页数据
        result.setData(userPage);
        return result;

    }
}
