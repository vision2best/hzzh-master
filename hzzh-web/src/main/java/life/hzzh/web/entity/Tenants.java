package life.hzzh.web.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 租户(Tenants)表实体类
 *
 * @author makejava
 * @since 2024-02-29 21:38:48
 */
@Setter
@Getter
public class Tenants extends Model<Tenants> {
    //租户id
    private String id;

    private String name;

    private String createBy;

    private String modifyBy;

    private Date createAt;

    private Date modifyAt;


}

