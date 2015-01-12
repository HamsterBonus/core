package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Merchant extends AEntity {

	private static final long serialVersionUID = 1L;

    private final String partner;
    private final MerchantState state;

	public Merchant(String id, String partner, MerchantState state) {
		super(id);
        this.state = Preconditions.checkNotNull(state);
        this.partner = Preconditions.checkNotNull(partner);
	}

    public MerchantState getState() {
        return state;
    }

    public String getPartner() {
        return partner;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                        .add("id", getId())
                        .add("state", state)
                        .add("partner", partner)
                            .toString();
    }

}
