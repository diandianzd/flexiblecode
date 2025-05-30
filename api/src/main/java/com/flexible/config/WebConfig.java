package com.flexible.config;

import com.flexible.config.mybatisPlus.MybatisInterceptor;
import com.flexible.core.annotation.CurrentUserMethodArgumentResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by  on 2018/10/27.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private MyRequestInterceptor myRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(myRequestInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/auth/login")
//                .excludePathPatterns("/druid/**")
                .excludePathPatterns("/index/**");
    }

    @Bean
    public FilterRegistrationBean requestFilterRegistration(MyRequestFilter myRequestFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(myRequestFilter);//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        //registration.addInitParameter("name", "alue");//添加默认参数
        registration.setName("MyRequestFilter");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }

    @Configuration
    public class ResourceConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            //可以访问localhost:8095/static/images/image.jpg
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
    }

    /**
     * CurrentUser 注解参数解析器
     *
     * @return
     */
    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

    /**
     * 参数解析器
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public MybatisInterceptor mybatisInterceptor() {
        return new MybatisInterceptor();
    }
}