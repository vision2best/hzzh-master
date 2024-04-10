package life.hzzh.acs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import life.hzzh.mybatisplus.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户角色(UserRoles)表实体类
 *
 * @author makejava
 * @since 2024-03-08 18:13:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoles extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    //用户 ID
    private Long userId;
    //角色 ID
    private Long roleId;
    //是否删除
    private Boolean isDeleted;
}

