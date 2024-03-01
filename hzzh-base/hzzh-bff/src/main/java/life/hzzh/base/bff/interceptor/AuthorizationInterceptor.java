package life.hzzh.base.bff.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ZHANG HUANG
 * 2024/3/1 14:34
 */
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 在请求处理之前进行权限控制
        // 这里可以检查当前用户是否有权限访问请求的资源，如果没有权限则拒绝访问
        if (!checkAuthorization(request)) {
            // 如果没有权限，可以返回错误信息或者重定向到拒绝访问页面
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 在请求处理之后但视图渲染之前执行，可对 ModelAndView 进行修改
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 在整个请求完成后执行，可进行一些资源清理工作
    }

    private boolean checkAuthorization(HttpServletRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("AuthorizationInterceptor...");
        }
        // 这里可以根据实际业务逻辑进行权限验证，比如从请求中获取用户信息，然后判断用户是否有权限访问请求的资源
        // 你可以根据自己的需求来实现具体的权限控制逻辑
        // 返回 true 表示有权限访问，返回 false 表示无权限访问
        return true;
    }
}
