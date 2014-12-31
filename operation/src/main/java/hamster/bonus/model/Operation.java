package hamster.bonus.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Operation extends AEntity {

	private static final long serialVersionUID = 1L;

	private final String merchant;
	private final String transaction;
	private final Amount amount;
	private final Amount bonus;
	
	public Operation(String id, String merchant, String transaction, Amount amount, Amount bonus) {
		super(id);
		this.merchant = Preconditions.checkNotNull(merchant);
		this.transaction = transaction;
		this.amount = Preconditions.checkNotNull(amount);
		this.bonus = bonus;
	}

	public String getMerchant() {
		return merchant;
	}

	public String getTransaction() {
		return transaction;
	}

	public Amount getAmount() {
		return amount;
	}

	public Amount getBonus() {
		return bonus;
	}
	
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                        .add("merchant", merchant)
                        .add("transaction", transaction)
                        .add("amount", amount)
                        .add("bonus", bonus)
                            .toString();
    }


}
