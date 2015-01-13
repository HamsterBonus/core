package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Payment extends AEntity {

	private static final long serialVersionUID = 1L;

    private final String partner;
	private final String merchant;
	private final String transaction;
	private final Amount amount;

	public Payment(String id, String partner, String merchant, String transaction, Amount amount) {
		super(id);
		this.partner = Preconditions.checkNotNull(partner);
        this.merchant = merchant;
		this.transaction = transaction;
		this.amount = Preconditions.checkNotNull(amount);
	}

    public String getPartner() {
        return partner;
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

	@Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                        .add("merchant", merchant)
                        .add("transaction", transaction)
                        .add("amount", amount)
                                .toString();
    }

}
