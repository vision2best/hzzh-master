package life.hzzh.midware.alibaba.oss.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {

    /**
     * bucket配置
     */
    private Map<String, OssBucketConf> buckets = new HashMap<>();

    /**
     * get bucket conf
     * @param bucketName
     * @return
     */
    public OssBucketConf getOssBucket(String bucketName){
        if(buckets == null || !buckets.containsKey(bucketName)) {
            return null;
        }
        return buckets.get(bucketName);
    }

}
