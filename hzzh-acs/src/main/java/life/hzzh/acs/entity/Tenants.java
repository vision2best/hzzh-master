package life.hzzh.acs.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 租户(Tenants)表实体类
 *
 * @author makejava
 * @since 2024-03-08 18:13:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenants extends Model<Tenants> {
    //租户 ID
    private Long tenantId;
    //租户名
    private String tenantName;
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

