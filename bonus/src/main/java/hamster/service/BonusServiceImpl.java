package hamster.service;

import com.google.common.base.Preconditions;
import hamster.balance.AmountBuilder;
import hamster.bonus.AmountCalculator;
import hamster.bonus.BonusData;
import hamster.bonus.PartnerChooser;
import hamster.bonus.PartnerPossibility;
import hamster.dao.BalanceDao;
import hamster.dao.PartnerBalanceDao;
import hamster.model.*;
import hamster.payment.PaymentBuilder;
import hamster.dao.PaymentDao;

import java.util.Collection;
import java.util.Currency;

/*
start method
test refactoring
code refactoring - command, java8 functions
tx tests
error code for exception
confirm method with security tests
partner state TEST
 */
public class BonusServiceImpl implements BonusService {

    private PaymentDao paymentDao;
    private PartnerChooser partnerChooser;
    private PartnerPossibility partnerPossibility;
    private AmountCalculator bonusAmountCalculator;

    public BonusServiceImpl(PaymentDao paymentDao,
                            PartnerPossibility partnerPossibility,
                            PartnerChooser partnerChooser,
                            AmountCalculator bonusAmountCalculator) {
        this.paymentDao = Preconditions.checkNotNull(paymentDao);
        this.partnerChooser = Preconditions.checkNotNull(partnerChooser);
        this.partnerPossibility = Preconditions.checkNotNull(partnerPossibility);
        this.bonusAmountCalculator = Preconditions.checkNotNull(bonusAmountCalculator);
    }

    @Override
	public PaymentBonus start(final BonusData data) {
        // check data values
        data.validate();
        // save payment
        Payment payment = paymentDao.save(PaymentBuilder.create(data.getPayment()).build());
        // choose partner checking status
        Partner partner = partnerChooser.get(data);
        // calculate bonus amount
        Amount bonusAmount = bonusAmountCalculator.calculate(data, partner.getId());
        // check partner
        partnerPossibility.check(partner.getId(), bonusAmount);
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
