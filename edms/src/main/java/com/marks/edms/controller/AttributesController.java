package com.marks.edms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AttributesController {
    @RequestMapping("/attributes")
    public String attributesIndex(ModelMap map) {
        map.put("title","Thymeleaf 标签演示");

        map.put("th_id","thymeleaf-input");
        map.put("th_name","thymeleaf-input");
        map.put("th_value","123");

        map.put("th_class","thymeleaf-class");
        map.put("th_href","https://www.baidu.com");

        return "attributes";
    }
}
