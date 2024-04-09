package life.hzzh.acs.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import life.hzzh.acs.dto.UserSignIn;
import life.hzzh.acs.dto.UserSignUp;
import life.hzzh.acs.entity.Users;
import life.hzzh.acs.kit.SecurityKit;
import life.hzzh.acs.security.entity.LoginUser;
import life.hzzh.acs.service.UsersService;
import life.hzzh.cache.RedisKit;
import life.hzzh.core.constant.CacheKey;
import life.hzzh.core.kit.TokenKit;
import life.hzzh.uid.kit.UidKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
@Slf4j
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
    @PostMapping("/sign-up")
    public ResponseEntity<Boolean> signUp(@RequestBody UserSignUp userSignUp) {
        //防止用户重复
        String password = passwordEncoder.encode(userSignUp.getPassword());
        var usersLambdaQueryWrapper = Wrappers.lambdaQuery(Users.class);
        usersLambdaQueryWrapper.eq(Users::getMobile, userSignUp.getMobile())
                .eq(Users::getPassword, password)
                .eq(Users::getIsDeleted, false);
        Users user = usersService.getOne(usersLambdaQueryWrapper);
        boolean save = !Objects.isNull(user);
        //不存在则新建
        if (!save) {
            log.info("user is not exist, create user, mobile:{}", userSignUp.getMobile());
            user = new Users();
            user.setUserId(UidKit.cachedUid());
            BeanUtil.copyProperties(userSignUp, user);
            user.setPassword(password);
            save = usersService.save(user);
        } else {
            log.warn("user is exist: mobile:{}", userSignUp.getMobile());
        }
        return ResponseEntity.ok(save);
    }

    /**
     * 登录
     *
     * @param userSignIn 登录信息
     * @return token
     */
    @PostMapping("/sign-in")
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
    @PostMapping("/sign-out")
    public ResponseEntity<Boolean> signOut() {
        Object userId = SecurityKit.getUserId();
        boolean b = RedisKit.deleteObject(String.format(CacheKey.LOGIN_USER_CACHE_KEY, userId));
        return ResponseEntity.ok(b);
    }
}