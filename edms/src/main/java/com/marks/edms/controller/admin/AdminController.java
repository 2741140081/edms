package com.marks.edms.controller.admin;

import com.marks.edms.entity.AdminUser;
import com.marks.edms.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminUserService adminUserService;

    @GetMapping("/indexAll")
    public String indexAll() {
        return "admin/index-all";
    }

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode, HttpSession session) {

        /**StringUtils.isEmpty()已经被弃用(deprecated)
         * 使用StringUtils.hasLength()或者StringUtils.hasText()替代
         * 区别：hasLength对于空格有效，后者hasText对于空格无效，也就是说纯空格的字符串使用hasText也会返回false
         */
        if (!StringUtils.hasLength(verifyCode)) {
            session.setAttribute("errorMsg","验证码不能为空");
            return "admin/login";
        }
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
            session.setAttribute("errorMsg","用户名或密码不能为空");
            return "admin/login";
        }
        verifyCode.toLowerCase();
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (!StringUtils.hasLength(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            System.out.println(kaptchaCode + "==========>" + verifyCode);
            session.setAttribute("errorMsg","验证码错误");
            return "admin/login";
        }

        AdminUser adminUser = adminUserService.login(userName, password);
        if (adminUser != null) {
            session.setAttribute("loginUser", adminUser.getLoginUserName());
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            //设置session过期时间
            session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/index";
        }else {
            session.setAttribute("errorMsg", "登录失败");
        }

        return "admin/login";
    }

}
