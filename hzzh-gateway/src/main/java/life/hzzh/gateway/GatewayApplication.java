package life.hzzh.gateway;

import jakarta.annotation.Resource;
import life.hzzh.gateway.dto.Users;
import life.hzzh.gateway.feign.AcsServiceFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@LoadBalancerClients({
        @LoadBalancerClient("service-gateway-provider")
})
//@EnableCaching
@EnableFeignClients
@RestController
public class GatewayApplication {

    @Resource
    private AcsServiceFeignClient acsServiceFeignClient;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}