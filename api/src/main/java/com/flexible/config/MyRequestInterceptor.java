package com.flexible.config;

import com.flexible.core.exception.UnAuthorityException;
import com.flexible.entity.Users;
import com.flexible.service.IUsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by  on 2018/10/27.
 */
@Component
public class MyRequestInterceptor implements HandlerInterceptor {
    @Lazy
    @Autowired
    IUsersService usersService;

    /*
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    /*
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    /*
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        // 如果是OPTIONS则结束请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        String requestUrl = request.getRequestURI();

        // 如果包含/login则放行
        if (requestUrl.contains("/login")) {
            return true;
        }


        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) throw new UnAuthorityException("token error");
        Users user = usersService.userAuth(token);
        if (user != null) {
            request.setAttribute("CurrentUser", user);
            return true;
        }
        throw new UnAuthorityException("token error");
    }
}