package life.hzzh.acs.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import life.hzzh.acs.kit.SecurityKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatisPlus_实体字段的自动填充
 *
 * @author ZHANG HUANG
 * 2024/3/13 18:09
 */
@Component
@Slf4j
public class MybatisPlusBaseHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        String userName = "admin";
        try {
            userName = (String) SecurityKit.getUserId();
        } catch (Exception e) {
            log.error("SecurityKit.getUserId() error:{}", e.toString());
        }
        // createBy和modifyBy字段为空时，才自动填充创建人和修改人的值
        Object createBy = getFieldValByName("createBy", metaObject);
        Object modifyBy = getFieldValByName("modifyBy", metaObject);
        if (createBy == null) {
            this.setFieldValByName("createBy", userName, metaObject);
        }
        if (modifyBy == null) {
            this.setFieldValByName("modifyBy", userName, metaObject);
        }
        this.setFieldValByName("createAt", LocalDateTime.now(), metaObject);
        this.setFieldValByName("modifyAt", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String userName = "admin";
        try {
            userName = (String) SecurityKit.getUserId();
        } catch (Exception e) {
            log.error("SecurityKit.getUserId() error:{}", e.toString());
        }
        Object modifyBy = getFieldValByName("modifyBy", metaObject);
        if (modifyBy == null) {
            this.setFieldValByName("modifyBy", userName, metaObject);
        }
        this.setFieldValByName("modifyAt", LocalDateTime.now(), metaObject);
    }


}
