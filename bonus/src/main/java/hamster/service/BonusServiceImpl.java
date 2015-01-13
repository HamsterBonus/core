package hamster.service;

import com.google.common.base.Preconditions;
import hamster.bonus.AmountCalculator;
import hamster.bonus.BonusData;
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
partner state TEST
 */
public class BonusServiceImpl implements BonusService {

    private PaymentDao paymentDao;
    private PartnerDao partnerDao;
    private AmountCalculator bonusAmountCalculator;

    public BonusServiceImpl(PaymentDao paymentDao,
                            PartnerDao partnerDao,
                            AmountCalculator bonusAmountCalculator) {
        this.paymentDao = Preconditions.checkNotNull(paymentDao);
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
        // check partner status
        //todo: separate component
        Partner partner = partnerDao.findOne(data.getPayment().getPartner());
        if(partner == null
                || !PartnerState.ACTIVE.equals(partner.getState())){
            throw new ValidationException("Partner id is incorrect");
        }

        // calculate bonus amount
        Amount bonusAmount = bonusAmountCalculator.calculate(data, partner.getId());
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
