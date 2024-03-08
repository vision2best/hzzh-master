package life.hzzh.acs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hzzh.acs.dao.TenantsDao;
import life.hzzh.acs.entity.Tenants;
import life.hzzh.acs.service.TenantsService;
import org.springframework.stereotype.Service;

/**
 * 租户(Tenants)表服务实现类
 *
 * @author makejava
 * @since 2024-03-08 18:13:06
 */
@Service("tenantsService")
public class TenantsServiceImpl extends ServiceImpl<TenantsDao, Tenants> implements TenantsService {

}

