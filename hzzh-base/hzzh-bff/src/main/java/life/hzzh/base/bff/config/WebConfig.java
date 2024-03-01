package life.hzzh.base.bff.config;

import life.hzzh.base.bff.interceptor.AuthorizationInterceptor;
import life.hzzh.base.bff.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZHANG HUANG
 * 2024/3/1 14:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .order(10)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/login", "/css/**", "/js/**", "/images/**"); // 排除登录页面和静态资源
        registry.addInterceptor(new AuthorizationInterceptor())
                .order(20)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/login", "/css/**", "/js/**", "/images/**"); // 排除登录页面和静态资源
    }
}
