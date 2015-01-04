package hamster.bonus.service;

import static org.junit.Assert.*;


import javax.annotation.Resource;

import hamster.bonus.IntegrationTestConfig;

import hamster.bonus.bonus.StartData;
import hamster.bonus.model.PaymentBonus;
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
        StartData data = new StartData();
        data.setAmountCurrency("RUB");
        data.setAmountValue("1");
        data.setMerchant("1");
		PaymentBonus bonus = bonusService.start(data);
        assertNotNull(bonus);
        assertNotNull(bonus.getPayment());
	}

}
