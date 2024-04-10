package life.hzzh.acs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZHANG HUANG
 * 2024/2/28 15:35
 */
@EnableCaching
@SpringBootApplication
@RestController
@MapperScan("life.hzzh.acs.dao")
@EnableDiscoveryClient
public class AcsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcsApplication.class, args);
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

}
