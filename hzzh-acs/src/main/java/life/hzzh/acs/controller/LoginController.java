package life.hzzh.acs.controller;

import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import life.hzzh.acs.constant.CacheKey;
import life.hzzh.acs.entity.Users;
import life.hzzh.acs.model.UserSignIn;
import life.hzzh.acs.model.UserSignUp;
import life.hzzh.acs.security.entity.LoginUser;
import life.hzzh.acs.service.UsersService;
import life.hzzh.cache.RedisKit;
import life.hzzh.core.kit.TokenKit;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 登录
 *
 * @author ZHANG HUANG
 * 2024/3/8 18:25
 */
@RestController
@RequestMapping
public class LoginController {
    @Resource
    private UsersService usersService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 注册
     *
     * @param userSignUp 注册信息
     * @return userId
     */
    @PostMapping("/sign_up")
    public ResponseEntity<Boolean> signUp(@RequestBody UserSignUp userSignUp) {
        Users user = new Users();
        user.setUserId(1000L);
        user.setCreateBy(1000L);
        user.setModifyBy(1000L);
        BeanUtil.copyProperties(userSignUp, user);
        user.setPassword(passwordEncoder.encode(userSignUp.getPassword()));
        boolean save = usersService.save(user);
        return ResponseEntity.ok(save);
    }

    /**
     * 登录
     *
     * @param userSignIn 登录信息
     * @return token
     */
    @PostMapping("/sign_in")
    public ResponseEntity<String> signIn(@RequestBody UserSignIn userSignIn) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userSignIn.getMobile(), userSignIn.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userId = loginUser.getUser().getUserId();
        String token = TokenKit.generateToken(userId);
        //authenticate存入redis
        RedisKit.setCacheObject(String.format(CacheKey.LOGIN_USER_CACHE_KEY, userId), loginUser);
        //把token响应给前端
        return ResponseEntity.ok(token);
    }

    /**
     * 登出
     *
     * @return boolean
     */
    @PostMapping("/sign_out")
    public ResponseEntity<Boolean> signOut() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();
        boolean b = RedisKit.deleteObject(String.format(CacheKey.LOGIN_USER_CACHE_KEY, userId));
        return ResponseEntity.ok(b);
    }
}