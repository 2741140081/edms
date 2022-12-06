package com.marks.edms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class NewBeeMallGoodsCategoryController {
    @GetMapping("/categories")
    public String category(HttpServletRequest request, @RequestParam("parentId") String parentId, @RequestParam("categoryLevel") String categoryLevel,
                           @RequestParam("backParentId") String backParentId){
        return null;
    }


}
