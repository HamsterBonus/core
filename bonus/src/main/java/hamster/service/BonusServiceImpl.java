package hamster.service;

import com.google.common.base.Preconditions;
import hamster.bonus.AmountCalculator;
import hamster.bonus.BonusData;
import hamster.bonus.PartnerChooser;
import hamster.bonus.ProgramChooser;
import hamster.dao.PaymentBonusDao;
import hamster.model.*;
import hamster.payment.PaymentBuilder;
import hamster.dao.PaymentDao;

/*
code refactoring - command, java8 functions
tx tests
error code for exception
confirm method with security tests
partner state TEST
logs
 */
public class BonusServiceImpl implements BonusService {

    private PaymentDao paymentDao;
    private PartnerChooser partnerChooser;
    private ProgramChooser programChooser;
    private AmountCalculator bonusAmountCalculator;
    private PaymentBonusDao paymentBonusDao;

    public BonusServiceImpl(PaymentDao paymentDao,
                            PartnerChooser partnerChooser,
                            ProgramChooser programChooser,
                            AmountCalculator bonusAmountCalculator,
                            PaymentBonusDao paymentBonusDao) {
        this.paymentDao = Preconditions.checkNotNull(paymentDao);
        this.partnerChooser = Preconditions.checkNotNull(partnerChooser);
        this.programChooser = Preconditions.checkNotNull(programChooser);
        this.bonusAmountCalculator = Preconditions.checkNotNull(bonusAmountCalculator);
        this.paymentBonusDao = Preconditions.checkNotNull(paymentBonusDao);
    }

    @Override
	public PaymentBonus start(final BonusData data) {
        // check data values
        data.validate();
        // save payment
        Payment payment = paymentDao.save(PaymentBuilder.create(data.getPayment()).build());
        // choose partner checking status
        Partner partner = partnerChooser.get(data, data.getPayment().getPartner());
        // choose bonus program, check that program exists or active
        BonusProgram program = programChooser.get(data, partner.getId());
        // calculate bonus amount
        Amount bonusAmount = bonusAmountCalculator.calculate(data, program, partner.getId());
        // save payment bonus
        return paymentBonusDao.save(new PaymentBonus(null, payment.getId(), partner.getId(), program.getId(), null, bonusAmount));
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
