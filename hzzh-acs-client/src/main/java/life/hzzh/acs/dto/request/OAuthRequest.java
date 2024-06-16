package life.hzzh.acs.dto.request;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OAuthRequest implements Serializable {
    @JSONField(name = "grant_type")
    private String grantType;

    @JSONField(name = "client_id")
    private String clientId;

    @JSONField(name = "client_secret")
    private String clientSecret;
    private String username;
    private String password;
}
