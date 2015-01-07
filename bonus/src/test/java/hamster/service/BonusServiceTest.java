package hamster.service;

import static org.junit.Assert.*;

import hamster.bonus.BonusData;
import hamster.model.PaymentBonus;
import hamster.test.Dictionary;
import hamster.validation.ValidationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BonusServiceTest extends AServiceTest {

	@Autowired
	BonusService bonusService;

	@Test
	public void testCorrectStart(){
        BonusData data = new BonusData();
        data.getPayment()
                .setMerchant(Dictionary.MERCHANT_ACTIVE_ID)
                .setAmountValue("1")
                .setAmountCurrency("RUB");
		PaymentBonus bonus = bonusService.start(data);
        assertNotNull(bonus);
        assertNotNull(bonus.getPayment());
	}

    @Test
    public void testEmptyMerchantInCreationData(){
        BonusData data = new BonusData();
        data.getPayment()
                .setAmountValue("1")
                .setAmountCurrency("RUB");
        try{
            bonusService.start(data);
            assertTrue(false);
        } catch (ValidationException e){
            assertTrue(true);
        } catch (Exception e){
            assertTrue(false);
        }

    }

    @Test
    public void testEmptyAmountInCreationData(){
        BonusData data = new BonusData();
        data.getPayment()
                .setMerchant(Dictionary.MERCHANT_ACTIVE_ID);
        try{
            bonusService.start(data);
            assertTrue(false);
        } catch (ValidationException e){
            assertTrue(true);
        } catch (Exception e){
            assertTrue(false);
        }

    }

}
