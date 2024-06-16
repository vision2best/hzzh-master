package life.hzzh.acs.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Builder
@Getter
@Setter
@Accessors(chain = true)
public class UserResponse implements Serializable {
}
