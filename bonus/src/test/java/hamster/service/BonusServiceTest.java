package hamster.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import hamster.bonus.SimpleCreationData;
import hamster.model.PaymentBonus;
import org.junit.Test;

public class BonusServiceTest extends AServiceTest {

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
