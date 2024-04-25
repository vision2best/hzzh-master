package life.hzzh.acs.dto;

import lombok.Data;

/**
 * @author ZHANG HUANG
 * 2024/3/11 15:22
 */
@Data
public class UserSignIn extends BaseModel {
    /**
     * 账号(手机号)
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
}
