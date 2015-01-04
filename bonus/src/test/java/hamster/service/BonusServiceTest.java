package hamster.service;

import static org.junit.Assert.*;


import javax.annotation.Resource;

import hamster.IntegrationTestConfig;

import hamster.bonus.SimpleCreationData;
import hamster.model.PaymentBonus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IntegrationTestConfig.class})
@ActiveProfiles("DB_H2")
public class BonusServiceTest {

	@Resource
	BonusService bonusService;
	
	@Test
	public void test(){
		PaymentBonus bonus = bonusService.start(
                new SimpleCreationData()
                        .setAmountValue("1")
                        .setAmountCurrency("RUB")
                        .setMerchant("1")
        );
        assertNotNull(bonus);
        assertNotNull(bonus.getPayment());
	}

}
