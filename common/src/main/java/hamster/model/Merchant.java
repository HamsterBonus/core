package hamster.model;

import java.util.Collection;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import hamster.state.State;

public class Merchant extends AEntity {

	private static final long serialVersionUID = 1L;

    private final MerchantState state;
	private final Collection<String> balances;
	
	public Merchant(String id, MerchantState state, Collection<String> balances) {
		super(id);
        this.state = Preconditions.checkNotNull(state);
		this.balances = balances;
	}

    public MerchantState getState() {
        return state;
    }

    public Collection<String> getBalances() {
		return balances;
	}

	@Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                        .add("state", state)
                        .add("balances", balances)
                            .toString();
    }

}
