package com.marks.edms.controller.mall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonalController {
    @GetMapping({"/login", "login.html"})
    public String loginPage() {
        return "/mall/login";
    }

    @GetMapping({"/register", "register.html"})
    public String registerPage() {
        return "/mall/register";
    }
}
