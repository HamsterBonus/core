package hamster.service;

import com.google.common.base.Preconditions;
import hamster.bonus.CreationData;
import hamster.model.*;
import hamster.payment.PaymentBuilder;
import hamster.dao.PaymentDao;

public class BonusServiceImpl implements BonusService {

    private PaymentDao paymentDao;

    public BonusServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = Preconditions.checkNotNull(paymentDao);
    }

    @Override
	public PaymentBonus start(CreationData data) {
        // check data values
        // save payment
        Payment payment = paymentDao.save(PaymentBuilder.create(data).build());
        // check merchant status
        // choose bonus program or check that program exists
        // calculate bonus amount if value is empty
        // save payment bonus
		return new PaymentBonus("1", payment.getId(), null, data.getBonusAmount());
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
