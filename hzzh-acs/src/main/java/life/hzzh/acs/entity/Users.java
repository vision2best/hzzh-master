package life.hzzh.acs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import life.hzzh.mybatisplus.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户(Users)表实体类
 *
 * @author makejava
 * @since 2024-03-08 18:13:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseEntity {
    //用户 ID
    @TableId(type = IdType.INPUT)
    private Long userId;
    //用户名
    private String username;
    //密码
    private String password;
    //手机号
    private String mobile;
    //邮箱
    private String email;
    //是否删除
    private Boolean isDeleted;
}

