package life.hzzh.midware.alibaba.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.StringUtils;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectResult;
import life.hzzh.midware.alibaba.oss.model.OssDTO;
import life.hzzh.midware.alibaba.oss.model.SignatureDTO;
import life.hzzh.midware.alibaba.oss.model.StsRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static life.hzzh.midware.alibaba.oss.OssClientFactory.ossClient;
import static life.hzzh.midware.alibaba.oss.OssClientFactory.ossProperties;
import static life.hzzh.midware.alibaba.oss.support.OssConstants.OssFileMaxSize;
import static life.hzzh.midware.alibaba.oss.support.OssConstants.SignatureDurationSeconds;


@Slf4j
@Component
public class OssHelper {

    /**
     * 使用STS临时访问凭证访问OSS
     * {@link(url = "<a href="https://help.aliyun.com/document_detail/100624.html">...</a>")}
     *
     * @param request sts 请求对象
     * @return ossDto
     */
    public static OssDTO getSts(StsRequestDTO request) {
        // 通过上传文件的目录进行签名
        String objectDir = generateFileKey(request.getObjectDirPrefix(), request.getMediaType(), null);
        SignatureDTO sts = genStsSignature(request.getBucketName(), objectDir, request.getFileSize());

        // basic info
        String region = ossProperties(request.getBucketName()).getRegion();
        String endpoint = ossProperties(request.getBucketName()).getEndpoint();

        return new OssDTO().setSts(sts)
                .setRegion(region)
                .setEndpoint(endpoint)
                .setBucket(request.getBucketName())
                .setObjectDir(objectDir);
    }


    /**
     * 上传文件
     *
     * @param bucketName bucketName
     * @param objectName 对象名
     * @param bytes      文件字节流
     * @return 上传结果
     */
    public static PutObjectResult upload(String bucketName, String objectName, byte[] bytes) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return ossClient(bucketName).putObject(bucketName, objectName, inputStream);
    }

    public static String genPreviewUrl(String bucketName, String objectName) {
        String ossEndPoint = ossProperties(bucketName).getEndpoint();
        return "https://" + bucketName + "." + ossEndPoint + "/" + objectName;
    }


    private static final String FileSeparator = "/";

    /**
     * 生成post签名
     *
     * @param bucketName bucketName
     * @param objectDir  存储目录
     * @return SignatureDTO
     */
    public static SignatureDTO genStsSignature(String bucketName, String objectDir, Long fileMaxSize) {
        // oss client build by sts
        OSS ossClient = ossClient(bucketName);

        // policy
        PolicyConditions conditions = new PolicyConditions();
        conditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, fileMaxSize > OssFileMaxSize ? fileMaxSize : OssFileMaxSize);
        conditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, objectDir);

        Date expiration = new Date(System.currentTimeMillis() + SignatureDurationSeconds * 1000);
        String policyConditionJson = ossClient.generatePostPolicy(expiration, conditions);
        if (log.isInfoEnabled()) {
            log.info("PolicyCondition: {}", policyConditionJson);
        }
        String policy = BinaryUtil.toBase64String(policyConditionJson.getBytes(StandardCharsets.UTF_8));

        // signature
        String signature = ossClient.calculatePostSignature(policyConditionJson);

        return new SignatureDTO()
                .setAccessKeyId(ossProperties(bucketName).getAccessKeyId())
                .setDuration(SignatureDurationSeconds)
                .setPolicy(policy).setSignature(signature);
    }

    /**
     * 文件是否存在
     *
     * @param bucketName bucketName
     * @param objectKey  文件
     * @return boolean
     */
    public static boolean exists(String bucketName, String objectKey) {
        OSS ossClient = ossClient(bucketName);
        return ossClient.doesObjectExist(bucketName, objectKey);
    }


    /**
     * oss file key
     *
     * @param id        根路径
     * @param mediaType 媒体类型
     * @param fileName  文件名
     * @return str
     */
    public static String generateFileKey(String id, String mediaType, String fileName) {
        if (org.springframework.util.StringUtils.hasLength(fileName)) {
            return StringUtils.join(FileSeparator, id, mediaType, fileName);
        }
        return StringUtils.join(FileSeparator, id, mediaType);
    }

}
