package life.hzzh.midware.alibaba.oss.support;

import lombok.Data;

import java.io.Serializable;


@Data
public class OssBucketConf implements Serializable {
    /**
     * 区域
     */
    private String region;

    /**
     * 访问端点
     */
    private String endpoint;

    /**
     * sts服务接入点
     */
    private String stsEndpoint;
    /**
     * 角色
     */
    private String roleArn;
    /**
     * 角色名称
     */
    private String roleSessionName;
    /**
     * access key id
     */
    private String accessKeyId;
    /**
     * access key secret
     */
    private String accessKeySecret;
    /**
     * cdn host
     */
    private String cdn;
}
