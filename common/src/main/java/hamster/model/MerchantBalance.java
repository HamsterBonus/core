package hamster.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class MerchantBalance extends AEntity {

    private static final long serialVersionUID = 1L;

    private final String merchant;
    private final String balance;

    public MerchantBalance(String id, String merchant, String balance) {
        super(id);
        this.merchant = Preconditions.checkNotNull(merchant);
        this.balance = Preconditions.checkNotNull(balance);
    }

    public String getMerchant() {
        return merchant;
    }

    public String getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", getId())
                .add("merchant", merchant)
                .add("balance", balance)
                .toString();
    }

}
