package hamster.bonus;

import com.google.common.base.Preconditions;
import hamster.dao.BalanceDao;
import hamster.dao.PartnerBalanceDao;
import hamster.model.Amount;
import hamster.model.Balance;
import hamster.model.PartnerBalance;

import java.util.Collection;
import java.util.Currency;

//todo: add over limits
public class SimplePartnerPossibility implements PartnerPossibility {

    private PartnerBalanceDao partnerBalanceDao;
    private BalanceDao balanceDao;

    public SimplePartnerPossibility(PartnerBalanceDao partnerBalanceDao, BalanceDao balanceDao) {
        this.partnerBalanceDao = Preconditions.checkNotNull(partnerBalanceDao);
        this.balanceDao = Preconditions.checkNotNull(balanceDao);
    }

    @Override
    public Amount check(String partner, Amount bonus) {
        //todo: make it more efficient
        Balance balance = findPartnerBalance(partner, bonus.getCurrency());
        if(balance == null
                || balance.getActiveValue().compareTo(bonus) < 0){
            //todo: throw exception
        }
        return bonus;
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

}
