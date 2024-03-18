package life.hzzh.midware.alibaba.oss;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.Protocol;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import life.hzzh.midware.alibaba.oss.support.OssBucketConf;
import life.hzzh.midware.alibaba.oss.support.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class OssClientFactory {

    private static OssProperties ossProperties;

    private static final Map<String, OSS> OssClientMap = new HashMap<>();
    private static final Map<String, DefaultAcsClient> AcsClientMap = new HashMap<>();

    @PostConstruct
    public void init() {
        for(String bucketName : ossProperties.getBuckets().keySet()) {
            createClient(bucketName);
        }
    }

    private void createClient(String bucketName) {
        OssBucketConf ossBucketConf = ossProperties.getOssBucket(bucketName);
        if(ossBucketConf == null) {
            return;
        }
        // oss client
        if(!OssClientMap.containsKey(bucketName)) {
            ClientBuilderConfiguration configuration = new ClientBuilderConfiguration();
            configuration.setProtocol(Protocol.HTTPS);
            OSS client = new OSSClientBuilder().build(ossBucketConf.getEndpoint(), ossBucketConf.getAccessKeyId(), ossBucketConf.getAccessKeySecret(), configuration);
            OssClientMap.put(bucketName, client);
        }
        // acs client
        if(!AcsClientMap.containsKey(bucketName)) {
            String endpoint = ossBucketConf.getStsEndpoint();
            DefaultProfile.addEndpoint(ossBucketConf.getRegion(), "Sts", endpoint);
            IClientProfile profile = DefaultProfile.getProfile(ossBucketConf.getRegion(), ossBucketConf.getAccessKeyId(), ossBucketConf.getAccessKeySecret());
            DefaultAcsClient acsClient = new DefaultAcsClient(profile);
            AcsClientMap.put(bucketName, acsClient);
        }
    }


    @Resource
    public void setOssProperties(OssProperties ossProperties) {
        OssClientFactory.ossProperties = ossProperties;
    }

    public static OSS ossClient(String bucketName) {
        return OssClientMap.get(bucketName);
    }

    public static DefaultAcsClient acsClient(String bucketName) {
        return AcsClientMap.get(bucketName);
    }

    public static OssBucketConf ossProperties(String bucketName) {
        return ossProperties.getOssBucket(bucketName);
    }

    private static final String ProtocolStr = Protocol.HTTPS.name().toLowerCase();

    public static String getEndpoint(String bucketName) {
        return ProtocolStr + "://" + bucketName + "." + ossProperties(bucketName).getEndpoint();
    }

    public static String getCdnUrl(String bucketName) {
        return ProtocolStr + "://" + ossProperties(bucketName).getCdn();
    }
}

