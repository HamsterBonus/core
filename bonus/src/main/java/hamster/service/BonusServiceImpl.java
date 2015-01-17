package hamster.service;

import com.google.common.base.Preconditions;
import hamster.bonus.AmountCalculator;
import hamster.bonus.BonusData;
import hamster.dao.PaymentBonusDao;
import hamster.model.*;
import hamster.payment.PaymentBuilder;
import hamster.dao.PaymentDao;

/*
test refactoring
code refactoring - command, java8 functions
tx tests
error code for exception
confirm method with security tests
partner state TEST
 */
public class BonusServiceImpl implements BonusService {

    private PaymentDao paymentDao;
    private AmountCalculator bonusAmountCalculator;
    private PaymentBonusDao paymentBonusDao;

    public BonusServiceImpl(PaymentDao paymentDao,
                            AmountCalculator bonusAmountCalculator,
                            PaymentBonusDao paymentBonusDao) {
        this.paymentDao = Preconditions.checkNotNull(paymentDao);
        this.bonusAmountCalculator = Preconditions.checkNotNull(bonusAmountCalculator);
        this.paymentBonusDao =Preconditions.checkNotNull(paymentBonusDao);
    }

    @Override
	public PaymentBonus start(final BonusData data) {
        // check data values
        data.validate();
        // save payment
        Payment payment = paymentDao.save(PaymentBuilder.create(data.getPayment()).build());
        // calculate bonus amount
        Amount bonusAmount = bonusAmountCalculator.calculate(data, data.getPayment().getPartner());
        // save payment bonus
        return paymentBonusDao.save(new PaymentBonus(null, payment.getId(), null, bonusAmount));
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
