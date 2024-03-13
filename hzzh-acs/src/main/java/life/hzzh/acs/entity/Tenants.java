package life.hzzh.acs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import life.hzzh.mybatisplus.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class Tenants extends BaseEntity {
    //租户 ID
    @TableId(type = IdType.INPUT)
    private Long tenantId;
    //租户名
    private String tenantName;
    //是否删除
    private Boolean isDeleted;

}

