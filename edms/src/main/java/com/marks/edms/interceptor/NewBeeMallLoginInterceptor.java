package com.marks.edms.interceptor;

import com.marks.edms.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class NewBeeMallLoginInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(NewBeeMallLoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("进入NewBeeMallLoginInterceptor拦截器");
        if (null == request.getSession().getAttribute(Constants.MALL_USER_SESSION_KEY) ) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
