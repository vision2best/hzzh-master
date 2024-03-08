package life.hzzh.acs.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class RolePermissions extends Model<RolePermissions> {
    //角色 ID
    private Long roleId;
    //权限 ID
    private Long permissionId;
    //创建人
    private Long createBy;
    //修改人
    private Long modifyBy;
    //创建时间
    private Date createAt;
    //修改时间
    private Date modifyAt;
    //是否删除
    private Boolean isDeleted;

}
