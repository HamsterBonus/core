package hamster.service;

import com.google.common.base.Preconditions;
import hamster.bonus.BonusData;
import hamster.dao.BonusProgramMerchantDao;
import hamster.dao.MerchantDao;
import hamster.model.*;
import hamster.payment.PaymentBuilder;
import hamster.dao.PaymentDao;
import hamster.validation.ValidationException;

import java.util.Collection;

/*
dao interface
start method
test refactoring
tx tests
error code foe exception
confirm method with security tests
 */
public class BonusServiceImpl implements BonusService {

    private PaymentDao paymentDao;
    private MerchantDao merchantDao;
    private BonusProgramMerchantDao bonusProgramMerchantDao;

    public BonusServiceImpl(PaymentDao paymentDao,
                            MerchantDao merchantDao,
                            BonusProgramMerchantDao bonusProgramMerchantDao) {
        this.paymentDao = Preconditions.checkNotNull(paymentDao);
        this.merchantDao = Preconditions.checkNotNull(merchantDao);
        this.bonusProgramMerchantDao = bonusProgramMerchantDao;
    }

    @Override
	public PaymentBonus start(BonusData data) {
        //todo: Action and Chain
        // check data values
        data.validate();
        // save payment
        Payment payment = paymentDao.save(PaymentBuilder.create(data.getPayment()).build());
        // check merchant status
        Merchant merchant = merchantDao.findOne(data.getPayment().getMerchant());
        if(merchant == null
                || !MerchantState.ACTIVE.equals(merchant.getState())){
            throw new ValidationException("Merchant id is incorrect");
        }
        // choose bonus program or check that program exists
        Collection<BonusProgramMerchant> programs = bonusProgramMerchantDao.findByMerchant(merchant.getId());

        // calculate bonus amount if value is empty and check merchant balance
        // save payment bonus
		return new PaymentBonus("1", payment.getId(), null, data.getAmount());
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
