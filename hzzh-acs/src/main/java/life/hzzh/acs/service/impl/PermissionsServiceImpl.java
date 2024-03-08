package life.hzzh.acs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hzzh.acs.dao.PermissionsDao;
import life.hzzh.acs.entity.Permissions;
import life.hzzh.acs.service.PermissionsService;
import org.springframework.stereotype.Service;

/**
 * 权限(Permissions)表服务实现类
 *
 * @author makejava
 * @since 2024-03-08 18:13:05
 */
@Service("permissionsService")
public class PermissionsServiceImpl extends ServiceImpl<PermissionsDao, Permissions> implements PermissionsService {

}

