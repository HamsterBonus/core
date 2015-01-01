package hamster.bonus.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import javax.annotation.Resource;

import hamster.bonus.IntegrationTestConfig;
import hamster.bonus.model.Amount;
import hamster.bonus.model.Operation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IntegrationTestConfig.class})
public class BonusServiceIntegrationTest {

	@Resource
	BonusService bonusService;
	
	@Test
	public void testCorrectStart(){
		Operation o = new Operation(null, "1", null, new Amount(BigDecimal.TEN, Currency.getInstance("RUB")), new Amount(BigDecimal.ONE, Currency.getInstance("RUB")));
		Operation first = check(o, bonusService.start(o));
		Operation second = check(o, bonusService.start(o));
		assertNotSame(first.getId(), second.getId());
	}
	
	private Operation check(Operation input, Operation result){
		assertNotNull(result);
		assertNotNull(result.getId());
		assertEquals(input.getAmount(), result.getAmount());
		assertEquals(input.getBonus(), result.getBonus());
		assertEquals(input.getMerchant(), result.getMerchant());
		return result;
	}
}
