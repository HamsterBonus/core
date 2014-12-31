package hamster.bonus.service;

import hamster.bonus.model.Account;
import hamster.bonus.model.Operation;
import hamster.bonus.model.Transaction;
import hamster.bonus.model.User;

public interface BonusService {

	Operation start(Operation data);
	
	Transaction linkUser(String operation, Account account);
	
	void confirm(String operation, User user);
	
}
