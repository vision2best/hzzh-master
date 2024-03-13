package life.hzzh.acs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import life.hzzh.mybatisplus.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色权限(RolePermissions)表实体类
 *
 * @author makejava
 * @since 2024-03-08 18:13:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissions extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    //角色 ID
    private Long roleId;
    //权限 ID
    private Long permissionId;
    //是否删除
    private Boolean isDeleted;
}

