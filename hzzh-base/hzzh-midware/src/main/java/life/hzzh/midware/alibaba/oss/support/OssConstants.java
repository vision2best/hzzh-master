package life.hzzh.midware.alibaba.oss.support;


public class OssConstants {
    /**
     * 最大20M
     */
    public static Long OssFileMaxSize = 20 * 1024 * 1024L;

    /**
     * 一小时秒数
     */
    public static Long HourSeconds = 1 * 60 * 60L;

    /**
     * 签名有效期 2小时
     */
    public static Long SignatureDurationSeconds = 1 * HourSeconds;

    /**
     * oss image/video process
     */
    public static String ImagePreviewProcess = "image/format,webp/interlace,1/quality,q_25";
}
