package life.hzzh.acs.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class Roles extends Model<Roles> {
    //角色 ID
    @TableId(type = IdType.INPUT)
    private Long roleId;
    //角色编码
    private String roleCode;
    //角色名
    private String roleName;
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

