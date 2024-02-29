package life.hzzh.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import life.hzzh.web.dao.UsersDao;
import life.hzzh.web.entity.Users;
import life.hzzh.web.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * 用户(Users)表服务实现类
 *
 * @author makejava
 * @since 2024-02-29 21:38:49
 */
@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, Users> implements UsersService {

}

