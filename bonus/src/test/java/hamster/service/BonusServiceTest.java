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
        data.getPayment().setMerchant(Dictionary.MERCHANT_WAITED);
        check(data, bonusService.start(data));
    }

    @Test
    public void testStartWithConfirmedMerchantForDisabledPartner(){
        BonusData data = correctStartData();
        data.getPayment().setMerchant(Dictionary.PARTNER_DISABLED);
        createWithValidationException(data);
    }

    @Test
    public void testStartWithConfirmedMerchantForActivePartnerWithoutBalance(){
        BonusData data = correctStartData();
        data.getPayment().setMerchant(Dictionary.MERCHANT_CONFIRMED);
        check(data, bonusService.start(data));
    }

    @Test
    public void testStartWithConfirmedMerchantForActivePartnerWithBalance(){
        BonusData data = correctStartData();
        data.getPayment().setMerchant(Dictionary.MERCHANT_CONFIRMED_WITH_BALANCE);
        checkWithPartner(data, Dictionary.PARTNER_MERCHANT_ACTIVE, bonusService.start(data));
    }

    @Test
    public void testStartWithDisabledPartner(){
        BonusData data = correctStartData();
        data.getPayment().setPartner(Dictionary.PARTNER_DISABLED);
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

    @Test
    public void testStartWithEmptyProgram(){
        BonusData data = correctStartData().setProgram(null);
        checkWithProgram(data, Dictionary.PROGRAM_ACTIVE_DEFAULT, bonusService.start(data));
    }

    @Test
    public void testStartWithNotExistedProgram(){
        createWithValidationException(correctStartData().setProgram("not_existed_program"));
    }

    @Test
    public void testStartWithNotActiveProgram(){
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
        check(input, input.getPayment().getPartner(), input.getProgram(), result);
    }

    private void checkWithPartner(BonusData input, String partner, PaymentBonus result){
        check(input, partner, input.getProgram(), result);
    }

    private void checkWithProgram(BonusData input, String program, PaymentBonus result){
        check(input, input.getPayment().getPartner(), program, result);
    }

    private void check(BonusData input, String partner, String program, PaymentBonus result){
        //todo: check payment record, compare with input data
        //todo: check count of records payment_bonus tables
        assertNotNull(result);
        assertNotNull(result.getPayment());
        assertEquals(partner, result.getPartner());
        assertEquals(program, result.getProgram());
        assertNull(result.getTransaction());
        assertTrue(result.getAmount().isPositive());
    }

    private BonusData correctStartData(){
        BonusData data = new BonusData()
                    .setProgram(Dictionary.PROGRAM_ACTIVE_DEFAULT);
        data.getPayment()
                .setPartner(Dictionary.PARTNER_ACTIVE)
                .setAmountValue("1")
                .setAmountCurrency("RUB");
        return data;
    }

}
