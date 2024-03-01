package life.hzzh.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import life.hzzh.base.bff.context.HeaderContext;
import life.hzzh.base.bff.context.PlatformEnum;
import life.hzzh.base.bff.context.TokenKit;
import life.hzzh.web.entity.Users;
import life.hzzh.web.model.LoginReq;
import life.hzzh.web.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static life.hzzh.base.core.eneity.AdminConstants.CommonTenantCode;

/**
 * @author ZHANG HUANG
 * 2024/3/1 18:58
 */
@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginReq req) {
        String mobile = req.getMobile();
        LambdaQueryWrapper<Users> usersLambdaQueryWrapper = Wrappers.lambdaQuery(Users.class);
        usersLambdaQueryWrapper.eq(Users::getMobile, mobile);
        Users users = usersService.getOne(usersLambdaQueryWrapper);
        if (users != null) {
            HeaderContext.HeaderData headerData = new HeaderContext.HeaderData();
            headerData.setTenantId(CommonTenantCode);
            headerData.setUserId(users.getId());
            headerData.setClient(PlatformEnum.WEB);
            headerData.setLoginAt(Instant.now().toEpochMilli());
            String token = TokenKit.generateToken(headerData);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
