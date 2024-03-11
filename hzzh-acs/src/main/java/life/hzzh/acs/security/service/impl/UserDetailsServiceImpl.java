package life.hzzh.acs.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import life.hzzh.acs.entity.Users;
import life.hzzh.acs.security.entity.LoginUser;
import life.hzzh.acs.service.UsersService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author ZHANG HUANG
 * 2024/3/11 13:44
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UsersService usersService;

    /**
     * @param username the username identifying the user whose data is required.
     * @return UserDetails
     * @throws UsernameNotFoundException 用户找不到
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usersLambdaQueryWrapper = Wrappers.lambdaQuery(Users.class);
        usersLambdaQueryWrapper.eq(Users::getMobile, username)
                .eq(Users::getIsDeleted, false);
        Users user = usersService.getOne(usersLambdaQueryWrapper);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("请检查登录信息");
        }
        return new LoginUser(user);
    }
}
