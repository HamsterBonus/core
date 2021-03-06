package hamster.service;

import hamster.bonus.BonusData;
import hamster.model.Account;
import hamster.model.PaymentBonus;
import hamster.model.Transaction;
import hamster.model.User;

public interface BonusService {

	PaymentBonus start(BonusData data);
	
	Transaction linkUser(String bonus, Account account);
	
	void confirm(String bonus, User manager);
	
}
