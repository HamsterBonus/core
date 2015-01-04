package hamster.service;

import com.google.common.base.Preconditions;
import hamster.bonus.StartData;
import hamster.model.*;
import hamster.repository.PaymentRepository;

public class BonusServiceImpl implements BonusService {

    private PaymentRepository paymentRepository;

    public BonusServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = Preconditions.checkNotNull(paymentRepository);
    }

    @Override
	public PaymentBonus start(StartData data) {
        Payment payment = paymentRepository.save(new Payment(null, data.getMerchant(), data.getTransaction(), data.getAmount()));
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
