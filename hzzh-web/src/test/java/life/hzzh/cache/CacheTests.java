package life.hzzh.cache;

import life.hzzh.web.WebApplication;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * @author ZHANG HUANG
 * 2024/2/28 18:11
 */
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CacheTests {
    @Test
    @Order(1)
    public void testCacheKit() {
        CacheKit.put("cache1", "hello", "word");
        Object o = CacheKit.get("cache1", "hello");
        Assert.isTrue(Objects.equals(o, "word"), o + "");
    }

    @Test
    @Order(2)
    public void testRedisKit() {
        RedisKit.setCacheObject("hello", "word");
        Object hello = RedisKit.getCacheObject("hello");
        Assert.isTrue(Objects.equals(hello, "word"), hello + "");
    }

    @Test
    @Order(3)
    public void testQueueKit() {
        boolean b = QueueKit.addQueueObject("queue1", "1");
        QueueKit.addQueueObject("queue2", "2");
        QueueKit.addQueueObject("queue3", "3");
        Assert.isTrue(b, b + "");
        Object queue1 = QueueKit.getQueueObject("queue1");
        System.out.println(queue1);
        QueueKit.removeQueueObject("queue1", "1");
        Object queue2 = QueueKit.getQueueObject("queue1");
        System.out.println(queue2);
    }
}
