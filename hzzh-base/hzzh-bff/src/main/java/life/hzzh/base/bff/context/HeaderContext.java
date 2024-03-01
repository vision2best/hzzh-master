package life.hzzh.base.bff.context;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZHANG HUANG
 * 2024/3/1 17:08
 */
public class HeaderContext {
    public static ThreadLocal<HeaderData> HeaderDataThreadLocal;

    static {
        HeaderDataThreadLocal = new ThreadLocal<>();
    }

    public static void setHeaderData(HeaderData headerData) {
        if (headerData == null) {
            return;
        }
        HeaderDataThreadLocal.set(headerData);
    }


    public static HeaderData getHeaderData() {
        return HeaderDataThreadLocal.get();
    }

    public static String getToken() {
        return getHeaderData().getToken();
    }

    public static String getTenantId() {
        return getHeaderData().getTenantId();
    }

    public static String getUserId() {
        return getHeaderData().getUserId();
    }

    public static List<String> getRoles() {
        return getHeaderData().getRoles();
    }

    public static PlatformEnum getClient() {
        return getHeaderData().getClient();
    }

    public static Long getLoginAt() {
        return getHeaderData().getLoginAt();
    }

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class HeaderData implements Serializable {

        private String token;

        private String tenantId;

        private String userId;

        private List<String> roles;

        private PlatformEnum client;

        private Long loginAt;

    }


}
