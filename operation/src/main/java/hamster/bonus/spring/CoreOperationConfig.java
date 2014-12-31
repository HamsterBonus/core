package hamster.bonus.spring;

import hamster.bonus.service.BonusService;
import hamster.bonus.service.BonusServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreOperationConfig {

	@Bean
	public BonusService bonusService(){
		return new BonusServiceImpl();
	}
}
