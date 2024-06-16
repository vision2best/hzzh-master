package life.hzzh.acs.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthResponse implements Serializable {
    private String accessToken;
}
