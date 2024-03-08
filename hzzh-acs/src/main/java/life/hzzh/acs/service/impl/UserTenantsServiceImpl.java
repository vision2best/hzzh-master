package life.hzzh.acs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hzzh.acs.dao.UserTenantsDao;
import life.hzzh.acs.entity.UserTenants;
import life.hzzh.acs.service.UserTenantsService;
import org.springframework.stereotype.Service;

/**
 * 用户租户(UserTenants)表服务实现类
 *
 * @author makejava
 * @since 2024-03-08 18:13:06
 */
@Service("userTenantsService")
public class UserTenantsServiceImpl extends ServiceImpl<UserTenantsDao, UserTenants> implements UserTenantsService {

}

