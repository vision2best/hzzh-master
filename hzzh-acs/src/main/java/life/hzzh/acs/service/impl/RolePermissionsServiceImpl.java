package life.hzzh.acs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hzzh.acs.dao.RolePermissionsDao;
import life.hzzh.acs.entity.RolePermissions;
import life.hzzh.acs.service.RolePermissionsService;
import org.springframework.stereotype.Service;

/**
 * 角色权限(RolePermissions)表服务实现类
 *
 * @author makejava
 * @since 2024-03-08 18:13:06
 */
@Service("rolePermissionsService")
public class RolePermissionsServiceImpl extends ServiceImpl<RolePermissionsDao, RolePermissions> implements RolePermissionsService {

}

