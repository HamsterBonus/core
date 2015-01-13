package hamster.spring;

import hamster.bonus.SimpleAmountCalculator;
import hamster.bonus.SimpleProgramChooser;
import hamster.dao.*;
import hamster.module.SqlScript;
import hamster.module.SqlScriptResource;
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
    BonusProgramPartnerDao bonusProgramPartnerDao;
    @Autowired
    BonusProgramDao bonusProgramDao;
    @Autowired
    PartnerDao partnerDao;


	@Bean
	public BonusService bonusService(){
		return new BonusServiceImpl(
                        paymentDao,
                        partnerDao,
                        new SimpleAmountCalculator(bonusProgramPartnerDao, new SimpleProgramChooser(bonusProgramDao))
        );
	}

    @Bean
    @Profile("DB_H2")
    public SqlScript h2CoreBonusScript(){
        return new SqlScriptResource(10, "db/h2/bonus.sql");
    }

}
