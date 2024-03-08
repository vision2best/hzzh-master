package life.hzzh.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import life.hzzh.web.entity.Users;
import life.hzzh.web.model.LoginReq;
import life.hzzh.web.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.ok("");
        }
        return ResponseEntity.badRequest().body(null);
    }
}
