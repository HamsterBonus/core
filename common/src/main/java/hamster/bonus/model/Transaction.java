package hamster.bonus.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Transaction implements Persistable<String>{

	private static final long serialVersionUID = 1L;

	private final String id;
	private final String sourceBalance;
	private final String destinationBalance;
	private final Amount amount;
	
	public Transaction(String id, String sourceBalance, String destinationBalance, Amount amount) {
		this.id = id;
		this.sourceBalance = Preconditions.checkNotNull(sourceBalance);
		this.destinationBalance = Preconditions.checkNotNull(destinationBalance);
		this.amount = Preconditions.checkNotNull(amount);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return StringUtils.isEmpty(id);
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
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) obj;
        return Objects.equal(id, other.id);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", id)
                        .add("sourceBalance", sourceBalance)
                        .add("destinationBalance", destinationBalance)
                        .add("amount", amount)
                            .toString();
    }
	
}
