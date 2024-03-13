package life.hzzh.uid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author ZHANG HUANG
 * 2024/3/13 16:18
 */
@Configuration
@EntityScan(basePackages = {"com.baidu.fsg.uid.worker.entity"})
@MapperScan(basePackages = {"com.baidu.fsg.uid.worker.dao"})
@ComponentScan("life.hzzh.uid")
@ImportResource(locations = {"classpath:uid-spring.xml"})
public class IDGeneratorAutoConfigure {


}
