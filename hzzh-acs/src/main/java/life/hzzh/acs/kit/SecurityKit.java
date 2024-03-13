package life.hzzh.acs.kit;

import life.hzzh.acs.entity.Users;
import life.hzzh.acs.security.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ZHANG HUANG
 * 2024/3/13 18:15
 */
@Slf4j
public class SecurityKit {
    public static Object getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            return loginUser.getUser().getUserId();
        } catch (Exception e) {
            return "default";
        }
    }

    public static Object getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser;
        try {
            loginUser = (LoginUser) authentication.getPrincipal();
        } catch (Exception e) {
            throw new RuntimeException("无效 Token");
        }
        return loginUser;
    }


    public static Object getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user;
        try {
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            user = loginUser.getUser();
        } catch (Exception e) {
            throw new RuntimeException("无效 Token");
        }
        return user;
    }
}
