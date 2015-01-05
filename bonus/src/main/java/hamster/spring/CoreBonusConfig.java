package hamster.spring;

import hamster.module.SqlScript;
import hamster.module.SqlScriptResource;
import hamster.dao.PaymentDao;
import hamster.service.BonusService;
import hamster.service.BonusServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class CoreBonusConfig {

    @Autowired
    PaymentDao paymentDao;

	@Bean
	public BonusService bonusService(){
		return new BonusServiceImpl(paymentDao);
	}

    @Bean
    @Profile("DB_H2")
    public SqlScript h2CoreBonusScript(){
        return new SqlScriptResource("db/h2/bonus.sql");
    }

}
