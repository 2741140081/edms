package com.marks.edms.controller.mall;

import com.marks.edms.common.Constants;
import com.marks.edms.common.ServiceResultEnum;
import com.marks.edms.controller.vo.NewBeeMallUserVO;
import com.marks.edms.entity.MallUser;
import com.marks.edms.service.NewBeeMallUserService;
import com.marks.edms.util.Result;
import com.marks.edms.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PersonalController {
    @Autowired
    private NewBeeMallUserService newBeeMallUserService;

    @GetMapping({"/login", "login.html"})
    public String loginPage() {
        return "/mall/login";
    }

    @GetMapping({"/register", "register.html"})
    public String registerPage() {
        return "/mall/register";
    }

    @GetMapping({"/reset", "reset.html"})
    public String resetPasswordPage() {
        return "/mall/reset";
    }


    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam("loginName") String loginName, @RequestParam("verifyCode") String verifyCode,
                           @RequestParam("password") String password,@RequestParam("emailAddress") String userEmail, HttpSession httpSession) {
        /**StringUtils.isEmpty()已经被弃用(deprecated)
         * 使用StringUtils.hasLength()或者StringUtils.hasText()替代
         * 区别：hasLength对于空格有效，后者hasText对于空格无效，也就是说纯空格的字符串使用hasText也会返回false
         */
        if (!StringUtils.hasText(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_NULL.getResult());
        }

        if (!StringUtils.hasText(userEmail)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }

        if (!StringUtils.hasText(loginName)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }

        if (!StringUtils.hasText(password)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }

        //将验证码全部转为小写,再去进行比较
        verifyCode.toLowerCase();
        String kaptchaCode = httpSession.getAttribute("verifyCode") + "";
        if (!StringUtils.hasText(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_ERROR.getResult());
        }

        String registerResult = newBeeMallUserService.register(loginName, password, userEmail);

        if (registerResult.equals(ServiceResultEnum.SUCCESS.getResult())) {
            //注册成功
            httpSession.removeAttribute(Constants.MALL_VERIFY_CODE_KEY);
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(registerResult);
    }

    @PostMapping("/resetPassword")
    @ResponseBody
    public Result resetPassword(@RequestParam("loginName") String loginName, @RequestParam("verifyCode") String verifyCode,
                           @RequestParam("userEmail") String userEmail, HttpSession httpSession) {
        /**StringUtils.isEmpty()已经被弃用(deprecated)
         * 使用StringUtils.hasLength()或者StringUtils.hasText()替代
         * 区别：hasLength对于空格有效，后者hasText对于空格无效，也就是说纯空格的字符串使用hasText也会返回false
         */
        if (!StringUtils.hasText(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_NULL.getResult());
        }

        if (!StringUtils.hasText(loginName)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }

        if (!StringUtils.hasText(userEmail)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.USER_EMAIL_NULL.getResult());
        }

        //将验证码全部转为小写,再去进行比较
        verifyCode.toLowerCase();
        String kaptchaCode = httpSession.getAttribute("verifyCode") + "";
        if (!StringUtils.hasText(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_ERROR.getResult());
        }

        String registerResult = newBeeMallUserService.register(loginName, userEmail, userEmail);

        if (registerResult.equals(ServiceResultEnum.SUCCESS.getResult())) {
            //注册成功
            httpSession.removeAttribute(Constants.MALL_VERIFY_CODE_KEY);
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(registerResult);
    }


    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("loginName") String loginName, @RequestParam("verifyCode") String verifyCode,
                           @RequestParam("password") String password, HttpSession httpSession) {
        /**StringUtils.isEmpty()已经被弃用(deprecated)
         * 使用StringUtils.hasLength()或者StringUtils.hasText()替代
         * 区别：hasLength对于空格有效，后者hasText对于空格无效，也就是说纯空格的字符串使用hasText也会返回false
         */
        if (!StringUtils.hasText(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_NULL.getResult());
        }

        if (!StringUtils.hasText(loginName)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }

        if (!StringUtils.hasText(password)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }

        //将验证码全部转为小写,再去进行比较
        verifyCode.toLowerCase();
        String kaptchaCode = httpSession.getAttribute("verifyCode") + "";
        if (!StringUtils.hasText(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_ERROR.getResult());
        }

        String loginResult = newBeeMallUserService.login(loginName, password, httpSession);

        if (loginResult.equals(ServiceResultEnum.SUCCESS.getResult())) {
            //验证登录成功
            httpSession.removeAttribute(Constants.MALL_VERIFY_CODE_KEY);
            return ResultGenerator.genSuccessResult();
        } else if (loginResult.equals(ServiceResultEnum.NEED_RESET_PASSWORD.getResult())) {
            httpSession.removeAttribute(Constants.MALL_VERIFY_CODE_KEY);
            return ResultGenerator.genResetPasswordResult();
        }
        return ResultGenerator.genFailResult(loginResult);
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute(Constants.MALL_USER_SESSION_KEY);
        return "mall/login";
    }

    @PostMapping("/personal/updateInfo")
    @ResponseBody
    public Result updateInfo(@RequestBody MallUser mallUser, HttpSession httpSession) {
        NewBeeMallUserVO mallUserTemp = newBeeMallUserService.updateUserInfo(mallUser, httpSession);
        if (mallUserTemp == null) {
            Result result = ResultGenerator.genFailResult("修改失败");
            return result;
        } else {
            //返回成功
            Result result = ResultGenerator.genSuccessResult();
            return result;
        }
    }

    @GetMapping("/personal")
    public String personalPage(HttpServletRequest request, HttpSession httpSession) {
        request.setAttribute("path", "personal");
        return "mall/personal";
    }

    @GetMapping("/personal/addresses")
    public String addressesPage() {
        return "mall/addresses";
    }
}
