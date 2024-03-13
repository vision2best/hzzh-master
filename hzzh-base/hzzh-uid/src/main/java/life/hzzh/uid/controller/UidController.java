package life.hzzh.uid.controller;

import life.hzzh.uid.kit.UidKit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UID
 *
 * @author ZHANG HUANG
 * 2024/3/13 16:22
 */
@RestController
@RequestMapping
public class UidController {

    /**
     * CachedUidGenerator
     *
     * @return
     */
    @GetMapping("/cache-uid")
    public Long getCacheUid() {
        return UidKit.cachedUid();
    }

    /**
     * DefaultUidGenerator
     *
     * @return
     */
    @GetMapping("/default-uid")
    public Long getDefaultUid() {
        return UidKit.defaultUid();
    }
}
