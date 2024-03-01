package life.hzzh.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZHANG HUANG
 * 2024/2/28 15:35
 */
@EnableCaching
@SpringBootApplication
@RestController
@MapperScan("life.hzzh.web.dao")
public class WebApplication {
    @GetMapping("/health")
    public String health(){
        return "ok";
    }
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
