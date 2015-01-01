package hamster.bonus.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class PaymentBonus extends AEntity {

	private static final long serialVersionUID = 1L;

	private final String payment;
	private final String transaction;
	private final Amount amount;
		
	public PaymentBonus(String id, String payment, String transaction, Amount amount) {
		super(id);
		this.payment = Preconditions.checkNotNull(payment);
		this.amount = Preconditions.checkNotNull(amount);
		this.transaction = transaction;
	}

	public String getPayment() {
		return payment;
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
                        .add("payment", payment)
                        .add("transaction", transaction)
                        .add("amount", amount)
                            .toString();
    }


}
