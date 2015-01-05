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
        Payment payment = paymentDao.save(PaymentBuilder.create(data).build());
		return new PaymentBonus("1", payment.getId(), null, data.getBonusAmount());
	}

	@Override
	public Transaction linkUser(String operation, Account account) {
		return null;
	}

	@Override
	public void confirm(String operation, User user) {
	}

}
