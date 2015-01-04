package hamster.service;

import com.google.common.base.Preconditions;
import hamster.bonus.CreationData;
import hamster.model.*;
import hamster.payment.PaymentBuilder;
import hamster.repository.PaymentRepository;

public class BonusServiceImpl implements BonusService {

    private PaymentRepository paymentRepository;

    public BonusServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = Preconditions.checkNotNull(paymentRepository);
    }

    @Override
	public PaymentBonus start(CreationData data) {
        Payment payment = paymentRepository.save(PaymentBuilder.create(data).build());
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
