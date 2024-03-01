package life.hzzh.web.args;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ZHANG HUANG
 * 2024/2/29 11:33
 */
@Slf4j
@Component
public class ArgsBean {
    public ArgsBean(ApplicationArguments args) {
        boolean b = args.containsOption("debug");
        if (b) {
            List<String> nonOptionArgs = args.getNonOptionArgs();
            nonOptionArgs.forEach(log::debug);
        }
    }
}
