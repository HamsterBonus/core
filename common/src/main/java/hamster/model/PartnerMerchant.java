package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class PartnerMerchant extends AEntity {

    private static final long serialVersionUID = 1L;

    private final String parent;
    private final String merchant;
    private final String partner;
    private final boolean useMerchantBalance;
    private final PartnerMerchantState state;

    public PartnerMerchant(String id, String parent, String merchant, String partner, boolean useMerchantBalance, PartnerMerchantState state) {
        super(id);
        this.parent = Preconditions.checkNotNull(parent);
        this.merchant = Preconditions.checkNotNull(merchant);
        this.partner = Preconditions.checkNotNull(partner);
        this.useMerchantBalance = useMerchantBalance;
        this.state = Preconditions.checkNotNull(state);
    }

    public String getParent() {
        return parent;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getPartner() {
        return partner;
    }

    public boolean isUseMerchantBalance() {
        return useMerchantBalance;
    }

    public PartnerMerchantState getState() {
        return state;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("partner", partner)
                .add("merchant", merchant)
                .add("parent", parent)
                .add("useMerchantBalance", useMerchantBalance)
                .add("state", state)
                .toString();
    }

}
