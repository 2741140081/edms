package com.marks.edms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

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

    @RequestMapping("/simple")
    public String simpleIndex(ModelMap map) {
        map.put("thymeleafText","spring-boot");
        map.put("number1", 2021);
        map.put("number2", 2);

        return "simple";
    }

    @RequestMapping("/complex")
    public String complexIndex(ModelMap map) {
        map.put("title", "Thymeleaf 语法测试");
        map.put("testString", "Spring Boot 商城");
        map.put("bool", true);
        map.put("testArray", new Integer[]{2021, 2022, 2023, 2024});
        map.put("testList", Arrays.asList("Spring", "Spring Boot", "Thymeleaf", "Mybatis", "Java"));
        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("platform", "book");
        testMap.put("title", "Spring Boot 商城项目实战");
        testMap.put("author", "十三");
        map.put("testMap", testMap);
        map.put("testDate", new Date());

        return "complex";
    }
}
