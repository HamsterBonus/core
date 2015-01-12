package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class PartnerBalance extends AEntity {

    private static final long serialVersionUID = 1L;

    private final String partner;
    private final String balance;

    public PartnerBalance(String id, String partner, String balance) {
        super(id);
        this.partner = Preconditions.checkNotNull(partner);
        this.balance = Preconditions.checkNotNull(balance);
    }

    public String getPartner() {
        return partner;
    }

    public String getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("partner", partner)
                .add("balance", balance)
                .toString();
    }

}
