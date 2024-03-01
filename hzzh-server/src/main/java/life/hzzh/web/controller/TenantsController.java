package life.hzzh.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import life.hzzh.base.bff.context.HeaderContext;
import life.hzzh.web.entity.Tenants;
import life.hzzh.web.service.TenantsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 租户(Tenants)表控制层
 *
 * @author makejava
 * @since 2024-02-29 21:39:29
 */
@RestController
@RequestMapping("tenants")
public class TenantsController {
    /**
     * 服务对象
     */
    @Resource
    private TenantsService tenantsService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<List<Tenants>> queryByPage(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                     @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        LambdaQueryWrapper<Tenants> tenantsLambdaQueryWrapper = Wrappers.lambdaQuery(Tenants.class);
        Page<Tenants> page = new Page<>(pageNum,pageSize);
        return ResponseEntity.ok(this.tenantsService.page(page, tenantsLambdaQueryWrapper).getRecords());
    }
}

