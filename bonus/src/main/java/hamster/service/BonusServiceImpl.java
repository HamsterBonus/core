package hamster.service;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import hamster.bonus.BonusData;
import hamster.dao.BonusProgramDao;
import hamster.dao.BonusProgramMerchantDao;
import hamster.dao.MerchantDao;
import hamster.error.SystemException;
import hamster.model.*;
import hamster.payment.PaymentBuilder;
import hamster.dao.PaymentDao;
import hamster.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/*
start method
test refactoring
code refactoring - command,dao interface
tx tests
error code for exception
confirm method with security tests
 */
public class BonusServiceImpl implements BonusService {

    private PaymentDao paymentDao;
    private MerchantDao merchantDao;
    private BonusProgramMerchantDao bonusProgramMerchantDao;
    private BonusProgramDao bonusProgramDao;
    public BonusServiceImpl(PaymentDao paymentDao,
                            MerchantDao merchantDao,
                            BonusProgramMerchantDao bonusProgramMerchantDao,
                            BonusProgramDao bonusProgramDao) {
        this.paymentDao = Preconditions.checkNotNull(paymentDao);
        this.merchantDao = Preconditions.checkNotNull(merchantDao);
        this.bonusProgramMerchantDao = Preconditions.checkNotNull(bonusProgramMerchantDao);
        this.bonusProgramDao = Preconditions.checkNotNull(bonusProgramDao);
    }

    @Override
	public PaymentBonus start(final BonusData data) {
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
        if(CollectionUtils.isEmpty(programs)){
            throw new SystemException("The list of bonus programs for merchant " + merchant.getId() + " is empty");
        }
        BonusProgramMerchant pm = Iterables.find(
                programs,
                new Predicate<BonusProgramMerchant>(){
                    @Override
                    public boolean apply(BonusProgramMerchant input) {
                        return StringUtils.isEmpty(data.getProgram())
                                ? input.isByDefault()
                                : data.getProgram().equals(input.getProgram());
                    }
                }
        );
        if(pm == null){
            throw new ValidationException("Can't choose active program");
        }
        BonusProgram program = bonusProgramDao.findOne(pm.getProgram());
        // calculate bonus amount if value is empty using bonus program data
        // check merchant balance
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
