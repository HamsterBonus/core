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
	public void testStartWithCorrectData(){
        BonusData data = correctStartData();
        check(data, bonusService.start(data));
	}

    @Test
    public void testStartWithWaitedMerchant(){
        BonusData data = correctStartData();
        data.getPayment().setMerchant(Dictionary.WAITED_MERCHANT);
        check(data, bonusService.start(data));
    }

    @Test
    public void testStartWithConfirmedMerchantForDisabledPartner(){
        BonusData data = correctStartData();
        data.getPayment().setMerchant(Dictionary.PARTNER_DISABLED_ID);
        createWithValidationException(data);
    }

    @Test
    public void testStartWithConfirmedMerchantForActivePartnerWithoutBalance(){
        BonusData data = correctStartData();
        data.getPayment().setMerchant(Dictionary.CONFIRMED_MERCHANT);
        check(data, bonusService.start(data));
    }

    @Test
    public void testStartWithConfirmedMerchantForActivePartnerWithBalance(){
        BonusData data = correctStartData();
        data.getPayment().setMerchant(Dictionary.CONFIRMED_MERCHANT_WITH_BALANCE);
        check(data, Dictionary.PARTNER_ACTIVE_MERCHANT, bonusService.start(data));
    }

    @Test
    public void testStartWithDisabledPartner(){
        BonusData data = correctStartData();
        data.getPayment().setPartner(Dictionary.PARTNER_DISABLED_ID);
        createWithValidationException(data);
    }

    @Test
    public void testStartWithUndefindPartner(){
        BonusData data = correctStartData();
        data.getPayment().setPartner(null);
        createWithValidationException(data);
    }

    @Test
    public void testStartWithEmptyAmount(){
        BonusData data = correctStartData();
        data.getPayment().setAmountValue(null).setAmountCurrency(null);
        createWithValidationException(data);
    }

    @Test
    public void testStartWithNegativeAmount(){
        BonusData data = correctStartData();
        data.getPayment().setAmountValue("-1");
        createWithValidationException(data);
    }

    private void createWithValidationException(BonusData data){
        try{
            bonusService.start(data);
            assertTrue(false);
        } catch (ValidationException e){
            assertTrue(true);
        } catch (Exception e){
            assertTrue(false);
        }
    }

    private void check(BonusData input, PaymentBonus result){
        check(input, input.getPayment().getPartner(), result);
    }

    private void check(BonusData input, String partner, PaymentBonus result){
        //todo: check payment record, compare with input data
        //todo: check count of records payment_bonus tables
        assertNotNull(result);
        assertNotNull(result.getPayment());
        assertEquals(partner, result.getPartner());
        assertNull(result.getTransaction());
        assertTrue(result.getAmount().isPositive());
    }

    private BonusData correctStartData(){
        BonusData data = new BonusData();
        data.getPayment()
                .setPartner(Dictionary.PARTNER_ACTIVE_ID)
                .setAmountValue("1")
                .setAmountCurrency("RUB");
        return data;
    }

}
