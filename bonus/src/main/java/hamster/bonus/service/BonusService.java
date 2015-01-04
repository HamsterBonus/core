package hamster.bonus.service;

import hamster.bonus.bonus.StartData;
import hamster.bonus.model.Account;
import hamster.bonus.model.PaymentBonus;
import hamster.bonus.model.Transaction;
import hamster.bonus.model.User;

public interface BonusService {

	PaymentBonus start(StartData data);
	
	Transaction linkUser(String operation, Account account);
	
	void confirm(String operation, User user);
	
}
