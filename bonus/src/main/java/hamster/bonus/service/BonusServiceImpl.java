package hamster.bonus.service;

import com.google.common.base.Preconditions;
import hamster.bonus.model.Account;
import hamster.bonus.model.PaymentBonus;
import hamster.bonus.model.Transaction;
import hamster.bonus.model.User;
import hamster.bonus.repository.PaymentRepository;

public class BonusServiceImpl implements BonusService {

    private PaymentRepository paymentRepository;

    public BonusServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = Preconditions.checkNotNull(paymentRepository);
    }

    @Override
	public PaymentBonus start(PaymentBonus data) {
		return null;
	}

	@Override
	public Transaction linkUser(String operation, Account account) {
		return null;
	}

	@Override
	public void confirm(String operation, User user) {
	}

}
