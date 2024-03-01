package life.hzzh.web.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户(Users)表实体类
 *
 * @author makejava
 * @since 2024-02-29 21:38:48
 */
@Setter
@Getter
public class Users extends Model<Users> {

    private String id;
    //用户名
    private String username;
    //手机号
    private String mobile;
    //邮箱
    private String email;

    private String createBy;

    private String modifyBy;

    private Date createAt;

    private Date modifyAt;


}

