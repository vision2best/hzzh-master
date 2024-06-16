package life.hzzh.acs.dto.request;

import life.hzzh.acs.dto.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ZHANG HUANG
 * 2024/3/11 14:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SignUpRequest extends BaseModel {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;
}
