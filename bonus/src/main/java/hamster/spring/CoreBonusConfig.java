package hamster.spring;

import hamster.bonus.*;
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
    ProgramPartnerDao programPartnerDao;
    @Autowired
    BonusProgramDao bonusProgramDao;
    @Autowired
    PartnerDao partnerDao;
    @Autowired
    PartnerMerchantDao partnerMerchantDao;
    @Autowired
    PartnerBalanceDao partnerBalanceDao;
    @Autowired
    BalanceDao balanceDao;
    @Autowired
    PaymentBonusDao paymentBonusDao;

	@Bean
	public BonusService bonusService(){
		return new BonusServiceImpl(
                        paymentDao,
                        new SimplePartnerChooser(partnerDao, partnerMerchantDao),
                        new SimpleProgramChooser(programPartnerDao, bonusProgramDao),
                        new AmountCalculatorDecorator(
                                new SimplePartnerPossibility(partnerBalanceDao, balanceDao),
                                new SimpleAmountCalculator()
                        ),
                        paymentBonusDao
        );
	}

    @Bean
    @Profile("DB_H2")
    public SqlScript h2CoreBonusScript(){
        return new SqlScriptResource(10, "db/h2/bonus.sql");
    }

}
