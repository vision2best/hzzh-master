package life.hzzh.uid.kit;

import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 分布式 ID
 *
 * @author ZHANG HUANG
 * 2024/3/13 17:02
 */
@Component
public class UidKit {
    @Resource
    private CachedUidGenerator cachedUidGenerator;
    @Resource
    private DefaultUidGenerator defaultUidGenerator;

    private static CachedUidGenerator cachedUid;
    private static DefaultUidGenerator defaultUid;

    @PostConstruct
    public void init() {
        cachedUid = cachedUidGenerator;
        defaultUid = defaultUidGenerator;
    }

    public static Long cachedUid() {
        return cachedUid.getUID();
    }

    public static Long defaultUid() {
        return defaultUid.getUID();
    }
}
