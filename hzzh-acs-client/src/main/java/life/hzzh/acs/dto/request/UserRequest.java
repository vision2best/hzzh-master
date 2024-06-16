package life.hzzh.acs.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Builder
@Getter
@Setter
@Accessors(chain = true)
public class UserRequest implements Serializable {
    //用户名
    private String username;
    //密码
    private String password;
    //手机号
    private String mobile;
    //邮箱
    private String email;
}
