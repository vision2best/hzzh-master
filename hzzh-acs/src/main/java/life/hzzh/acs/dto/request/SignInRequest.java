package life.hzzh.acs.dto.request;

import life.hzzh.acs.dto.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ZHANG HUANG
 * 2024/3/11 15:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SignInRequest extends BaseModel {
    /**
     * 账号(手机号)
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
}
