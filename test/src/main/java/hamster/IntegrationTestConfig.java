package hamster;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"hamster.spring"})
public class IntegrationTestConfig {

}
