package hamster.bonus.spring;

import hamster.bonus.module.SqlScript;
import hamster.bonus.module.SqlScriptResource;
import hamster.bonus.service.BonusService;
import hamster.bonus.service.BonusServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class CoreBonusConfig {

	@Bean
	public BonusService bonusService(){
		return new BonusServiceImpl();
	}

    @Bean
    @Profile("DB_H2")
    public SqlScript h2CoreBonusScript(){
        return new SqlScriptResource("db/h2/bonus.sql");
    }

}
