package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Transaction extends AEntity {

	private static final long serialVersionUID = 1L;

	private final String sourceBalance;
	private final String destinationBalance;
	private final Amount amount;
	
	public Transaction(String id, String sourceBalance, String destinationBalance, Amount amount) {
		super(id);
		this.sourceBalance = Preconditions.checkNotNull(sourceBalance);
		this.destinationBalance = Preconditions.checkNotNull(destinationBalance);
		this.amount = Preconditions.checkNotNull(amount);
	}

	public String getSourceBalance() {
		return sourceBalance;
	}

	public String getDestinationBalance() {
		return destinationBalance;
	}

	public Amount getAmount() {
		return amount;
	}

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                        .add("sourceBalance", sourceBalance)
                        .add("destinationBalance", destinationBalance)
                        .add("amount", amount)
                            .toString();
    }
	
}
