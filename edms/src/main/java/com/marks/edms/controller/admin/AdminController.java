package com.marks.edms.controller.admin;

import com.marks.edms.entity.AdminUser;
import com.marks.edms.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
        //将验证码全部转为小写,再去进行比较
        verifyCode.toLowerCase();
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (!StringUtils.hasLength(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
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

    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        System.out.println(loginUserId);
        AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
        if (adminUser == null) {
            return "admin/login";
        }
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", adminUser.getLoginUserName());
        request.setAttribute("nickName", adminUser.getNickName());

        return "/admin/profile";
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassowrd, @RequestParam("newPassword") String newPassword) {
        if (!StringUtils.hasLength(originalPassowrd)) {
            return "参数不能为空";
        }
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");

        if (adminUserService.updatePassword(loginUserId, originalPassowrd, newPassword)) {
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return "success";
        } else {
            return "修改失败";
        }

    }

    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName, @RequestParam("nickName") String nickName) {
        if (!StringUtils.hasLength(loginUserName) || !StringUtils.hasLength(nickName) ) {
            return "参数不能为空";
        }
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");

        if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
            return "success";
        } else {
            return "修改失败";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "/admin/login";
    }

}
