package hamster.spring;

import hamster.dao.MerchantDao;
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
    @Autowired
    MerchantDao merchantDao;

	@Bean
	public BonusService bonusService(){
		return new BonusServiceImpl(paymentDao, merchantDao);
	}

    @Bean
    @Profile("DB_H2")
    public SqlScript h2CoreBonusScript(){
        return new SqlScriptResource("db/h2/bonus.sql");
    }

}
