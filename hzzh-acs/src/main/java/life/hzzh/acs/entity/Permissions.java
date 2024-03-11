package life.hzzh.acs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限(Permissions)表实体类
 *
 * @author makejava
 * @since 2024-03-08 18:13:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permissions extends Model<Permissions> {
    //权限 ID
    @TableId(type = IdType.INPUT)
    private Long permissionId;
    //权限编码
    private String permissionCode;
    //权限名
    private String permissionName;
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

