package hamster.spring;

import hamster.bonus.*;
import hamster.dao.*;
import hamster.model.Amount;
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
                        new AmountCalculatorDecorator(
                                new SimplePartnerChooser(partnerDao, partnerMerchantDao),
                                new SimplePartnerPossibility(partnerBalanceDao, balanceDao),
                                new SimpleAmountCalculator(bonusProgramPartnerDao, new SimpleProgramChooser(bonusProgramDao))
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
