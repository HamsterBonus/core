package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Merchant extends AEntity {

	private static final long serialVersionUID = 1L;

    private final MerchantState state;

	public Merchant(String id, MerchantState state) {
		super(id);
        this.state = Preconditions.checkNotNull(state);
	}

    public MerchantState getState() {
        return state;
    }

	@Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                        .add("state", state)
                            .toString();
    }

}
