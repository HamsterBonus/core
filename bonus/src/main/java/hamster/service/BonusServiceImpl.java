package hamster.service;

import com.google.common.base.Preconditions;
import hamster.bonus.AmountCalculator;
import hamster.bonus.BonusData;
import hamster.dao.MerchantDao;
import hamster.dao.PartnerDao;
import hamster.model.*;
import hamster.payment.PaymentBuilder;
import hamster.dao.PaymentDao;
import hamster.validation.ValidationException;

/*
start method
test refactoring
code refactoring - command
tx tests
error code for exception
confirm method with security tests
partner parameter instead of merchant, merchant is additional and optional
merchant state TEST
 */
public class BonusServiceImpl implements BonusService {

    private PaymentDao paymentDao;
    private MerchantDao merchantDao;
    private PartnerDao partnerDao;
    private AmountCalculator bonusAmountCalculator;

    public BonusServiceImpl(PaymentDao paymentDao,
                            MerchantDao merchantDao,
                            PartnerDao partnerDao,
                            AmountCalculator bonusAmountCalculator) {
        this.paymentDao = Preconditions.checkNotNull(paymentDao);
        this.merchantDao = Preconditions.checkNotNull(merchantDao);
        this.partnerDao = Preconditions.checkNotNull(partnerDao);
        this.bonusAmountCalculator = Preconditions.checkNotNull(bonusAmountCalculator);
    }

    @Override
	public PaymentBonus start(final BonusData data) {
        //todo: Action and Chain
        // check data values
        data.validate();
        // save payment
        Payment payment = paymentDao.save(PaymentBuilder.create(data.getPayment()).build());
        // check merchant and partner status
        //todo: separate component
        //todo: common for partner, merchant etc
        final Merchant merchant = merchantDao.findOne(data.getPayment().getMerchant());
        if(merchant == null
                || !MerchantState.ACTIVE.equals(merchant.getState())){
            throw new ValidationException("Merchant id is incorrect");
        }
        Partner partner = partnerDao.findOne(merchant.getPartner());
        if(partner == null
                || !PartnerState.ACTIVE.equals(partner.getState())){
            throw new ValidationException("Partner is incorrect");
        }

        // calculate bonus amount
        Amount bonusAmount = bonusAmountCalculator.calculate(data, merchant.getId());
        // check partner balance
        // save payment bonus
		return new PaymentBonus("1", payment.getId(), null, bonusAmount);
	}

	@Override
	public Transaction linkUser(String bonus, Account account) {
        // check user exists
        // if not create user and save account
        // find bonus and check that transaction is empty
        // save transaction
        // save bonus with transaction value
		return null;
	}

	@Override
	public void confirm(String bonus, User manager) {
        // check user has permission to do operation
        // check transaction status
        // handle transaction
	}

}
