package com.marks.edms.controller;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;


@Controller
public class KaptchaController {

    @GetMapping("kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/gif");

        //三个参数分别设置宽，高和位数
        SpecCaptcha captcha = new SpecCaptcha(75, 30, 4);

        //设置类型为数字和字母混合型
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        //设置字体
        captcha.setCharType(Captcha.FONT_9);

        //验证码存入session中
        httpServletRequest.getSession().setAttribute("verifyCode", captcha.text().toLowerCase(Locale.ROOT));

        //输出图片流
        captcha.out(httpServletResponse.getOutputStream());
    }

    @GetMapping("/verify")
    @ResponseBody
    public String verity(@RequestParam("code") String code, HttpSession session) {
        if (!StringUtils.hasLength(code)){
            return "验证码不能为空";
        }

        String kaptchaCode = session.getAttribute("verifyCode")  + "";
        System.out.println(code);
        System.out.println("================");
        System.out.println(kaptchaCode);

        if (!StringUtils.hasLength(kaptchaCode) || !code.toLowerCase().equals(kaptchaCode)) {
            return "验证码错误";
        }

        return "验证成功";
    }
}
