package hamster.bonus.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class User extends AEntity{

	private static final long serialVersionUID = 1L;

	private final String balance;
	
	public User(String id, String balance) {
		super(id);
		this.balance = Preconditions.checkNotNull(balance);
	}

	public String getBalance() {
		return balance;
	}
	
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                        .add("balance", balance)
                            .toString();
    }

}
