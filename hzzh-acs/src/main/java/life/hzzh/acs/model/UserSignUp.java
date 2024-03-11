package life.hzzh.acs.model;

import lombok.Data;

/**
 * @author ZHANG HUANG
 * 2024/3/11 14:09
 */
@Data
public class UserSignUp extends BaseModel {
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
