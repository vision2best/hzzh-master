package life.hzzh.midware.alibaba.oss.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;


@Data
public class StsRequestDTO implements Serializable {

    /**
     * bucket name
     */
    @NotEmpty
    private String bucketName;
    /**
     * oss object name prefix
     * objectName = ${objectDirPrefix}/${mediaType}/${fileName}
     */
    @NotEmpty
    private String objectDirPrefix;
    /**
     * 媒体类型
     */
    @NotEmpty
    private String mediaType;
    /**
     * 文件名
     */
    @NotEmpty
    private String fileName;

    @NotEmpty
    @Min(value = 0)
    // 最大1G
    @Max(value = 1024 * 1024 * 1024L)
    private Long fileSize;

}
