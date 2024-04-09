package life.hzzh.gateway.feign;

import life.hzzh.gateway.dto.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ZHANG HUANG
 * 2024/4/9 15:03
 */
@FeignClient(value = "hzzh-acs")
@Component
public interface AcsServiceFeignClient {
//    @RequestMapping(method = RequestMethod.GET, value = "/user/{userid}")
//    ResponseEntity<Users> getUserByUseid(@PathVariable("userid") Long userid);

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userName}")
    Users getUserByUsername(@PathVariable("username") String username);
}
