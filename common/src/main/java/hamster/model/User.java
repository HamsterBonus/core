package hamster.model;

import java.util.Collection;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class User extends AEntity{

	private static final long serialVersionUID = 1L;

	private final Collection<String> balances;
	
	public User(String id, Collection<String> balances) {
		super(id);
		this.balances = Preconditions.checkNotNull(balances);
	}

	public Collection<String> getBalances() {
		return balances;
	}
	
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                        .add("balances", balances)
                            .toString();
    }

}
