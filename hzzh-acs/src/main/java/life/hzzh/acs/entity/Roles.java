package life.hzzh.acs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import life.hzzh.mybatisplus.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色(Roles)表实体类
 *
 * @author makejava
 * @since 2024-03-08 18:13:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles extends BaseEntity {
    //角色 ID
    @TableId(type = IdType.INPUT)
    private Long roleId;
    //角色编码
    private String roleCode;
    //角色名
    private String roleName;
    //是否删除
    private Boolean isDeleted;
}

