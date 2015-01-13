package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class PartnerMerchant extends AEntity {

    private static final long serialVersionUID = 1L;

    private final String parent;
    private final String merchant;
    private final String partner;
    private final boolean useMerchantBalance;

    public PartnerMerchant(String id, String parent, String merchant, String partner, boolean useMerchantBalance) {
        super(id);
        this.parent = Preconditions.checkNotNull(parent);
        this.merchant = Preconditions.checkNotNull(merchant);
        this.partner = Preconditions.checkNotNull(partner);
        this.useMerchantBalance = useMerchantBalance;
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

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("partner", partner)
                .add("merchant", merchant)
                .add("parent", parent)
                .add("useMerchantBalance", useMerchantBalance)
                .toString();
    }

}
