package life.hzzh.acs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hzzh.acs.dao.RolesDao;
import life.hzzh.acs.entity.Roles;
import life.hzzh.acs.service.RolesService;
import org.springframework.stereotype.Service;

/**
 * 角色(Roles)表服务实现类
 *
 * @author makejava
 * @since 2024-03-08 18:13:06
 */
@Service("rolesService")
public class RolesServiceImpl extends ServiceImpl<RolesDao, Roles> implements RolesService {

}

