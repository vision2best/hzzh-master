package life.hzzh.midware.alibaba.oss.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
public class SignatureDTO implements Serializable {
    /**
     * 临时access key id
     */
    private String accessKeyId;

    /**
     * 临时access key secret
     */
    private String accessKeySecret;

    /**
     * 临时token
     */
    private String securityToken;

    /**
     * 有效期 时间单位秒
     */
    private Long duration;

    /**
     * sts token
     */
    private String signature;
    /**
     * sts policy
     */
    private String policy;
}
