package life.hzzh.acs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hzzh.acs.dao.UsersDao;
import life.hzzh.acs.entity.Users;
import life.hzzh.acs.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * 用户(Users)表服务实现类
 *
 * @author makejava
 * @since 2024-03-08 18:13:06
 */
@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements UsersService {

}

