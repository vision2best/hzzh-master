package life.hzzh.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author ZHANG HUANG
 * 2024/3/1 19:00
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq implements Serializable {
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String captcha;
}
