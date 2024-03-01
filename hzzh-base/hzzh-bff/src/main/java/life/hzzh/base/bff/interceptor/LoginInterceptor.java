package life.hzzh.base.bff.interceptor;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import life.hzzh.base.bff.context.HeaderContext;
import life.hzzh.base.bff.context.TokenKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static life.hzzh.base.bff.context.HeaderConstants.Authorization;

/**
 * @author ZHANG HUANG
 * 2024/3/1 14:28
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!checkLogin(request)) {
            // 如果没有权限，可以返回错误信息或者重定向到拒绝访问页面
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please Login");
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean checkLogin(HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("LoginInterceptor...");
        }
        String token = request.getHeader(Authorization);
        Boolean validated = TokenKit.validateToken(token);
        if (!validated) {
            return false;
        }
        HeaderContext.HeaderData headerData = TokenKit.parseToken(token);
        HeaderContext.setHeaderData(headerData);
        if (log.isDebugEnabled()) {
            log.debug("LoginInterceptor.HeaderDate:\n{}", JSON.toJSONString(headerData));
        }
        return true;
    }

}
