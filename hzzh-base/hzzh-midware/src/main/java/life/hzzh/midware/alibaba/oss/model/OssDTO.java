package life.hzzh.midware.alibaba.oss.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
public class OssDTO implements Serializable {

    /**
     * bucket name
     */
    private String bucket;

    /**
     * oss region
     */
    private String region;

    /**
     * oss endpoint
     */
    private String endpoint;

    /**
     * oss文件路径, 不包含bucket name
     */
    private String objectDir;

    /**
     * sts
     */
    private SignatureDTO sts;

}

