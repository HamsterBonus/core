package hamster.service;

import com.google.common.base.Preconditions;
import hamster.balance.AmountBuilder;
import hamster.bonus.AmountCalculator;
import hamster.bonus.BonusData;
import hamster.bonus.PartnerChooser;
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
    private PartnerBalanceDao partnerBalanceDao;
    private BalanceDao balanceDao;
    private AmountCalculator bonusAmountCalculator;

    public BonusServiceImpl(PaymentDao paymentDao,
                            PartnerBalanceDao partnerBalanceDao,
                            BalanceDao balanceDao,
                            PartnerChooser partnerChooser,
                            AmountCalculator bonusAmountCalculator) {
        this.paymentDao = Preconditions.checkNotNull(paymentDao);
        this.partnerChooser = Preconditions.checkNotNull(partnerChooser);
        this.partnerBalanceDao = Preconditions.checkNotNull(partnerBalanceDao);
        this.balanceDao = Preconditions.checkNotNull(balanceDao);
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
        // check partner balance
        Collection<PartnerBalance> balances = partnerBalanceDao.findByPartner(partner.getId());
        //todo: make it more efficient
        Balance balance = findPartnerBalance(partner.getId(), bonusAmount.getCurrency());
        if(balance == null
                || balance.getActiveValue().compareTo(bonusAmount) < 0){
            //todo: throw exception
        }
        // save payment bonus
		return new PaymentBonus("1", payment.getId(), null, bonusAmount);
	}

    private Balance findPartnerBalance(String partner, Currency c){
        Collection<PartnerBalance> balances = partnerBalanceDao.findByPartner(partner);
        //todo: make it more efficient
        for(PartnerBalance b : balances){
            Balance balance = balanceDao.findOne(b.getBalance());
            if(c.equals(balance.getActiveValue().getCurrency())){
                return balance;
            }
        }
        return null;
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
