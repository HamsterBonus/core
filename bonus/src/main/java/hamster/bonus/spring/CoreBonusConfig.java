package hamster.bonus.spring;

import hamster.bonus.service.BonusService;
import hamster.bonus.service.BonusServiceImpl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class CoreBonusConfig implements InitializingBean{

    @Autowired
    ResourceDatabasePopulator resourceDatabasePopulator;

	@Bean
	public BonusService bonusService(){
		return new BonusServiceImpl();
	}

    @Override
    public void afterPropertiesSet() throws Exception {
        //todo: check active profile
        resourceDatabasePopulator.addScript(new ClassPathResource("db/h2/bonus.sql"));
    }
}
