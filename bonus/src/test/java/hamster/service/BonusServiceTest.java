package hamster.service;

import static org.junit.Assert.*;

import hamster.bonus.SimpleCreationData;
import hamster.model.PaymentBonus;
import hamster.test.DataSets;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataSets(setUpDataSet="/db/TestData.xls")
public class BonusServiceTest extends AServiceTest {

	@Autowired
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
