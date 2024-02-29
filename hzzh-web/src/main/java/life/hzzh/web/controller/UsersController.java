package life.hzzh.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import life.hzzh.web.entity.Users;
import life.hzzh.web.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户(Users)表控制层
 *
 * @author makejava
 * @since 2024-02-29 21:39:29
 */
@RestController
@RequestMapping("users")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<List<Users>> queryByPage(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                   @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        LambdaQueryWrapper<Users> usersLambdaQueryWrapper = Wrappers.lambdaQuery(Users.class);
        Page<Users> page = new Page<>(pageNum, pageSize);
        return ResponseEntity.ok(this.usersService.page(page, usersLambdaQueryWrapper).getRecords());
    }


}

