package life.hzzh.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hzzh.web.dao.TenantsDao;
import life.hzzh.web.entity.Tenants;
import life.hzzh.web.service.TenantsService;
import org.springframework.stereotype.Service;

/**
 * 租户(Tenants)表服务实现类
 *
 * @author makejava
 * @since 2024-02-29 21:38:48
 */
@Service("tenantsService")
public class TenantsServiceImpl extends ServiceImpl<TenantsDao, Tenants> implements TenantsService {

}

