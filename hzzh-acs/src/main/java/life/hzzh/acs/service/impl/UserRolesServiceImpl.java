package life.hzzh.acs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hzzh.acs.dao.UserRolesDao;
import life.hzzh.acs.entity.UserRoles;
import life.hzzh.acs.service.UserRolesService;
import org.springframework.stereotype.Service;

/**
 * 用户角色(UserRoles)表服务实现类
 *
 * @author makejava
 * @since 2024-03-08 18:13:06
 */
@Service("userRolesService")
public class UserRolesServiceImpl extends ServiceImpl<UserRolesDao, UserRoles> implements UserRolesService {

}

