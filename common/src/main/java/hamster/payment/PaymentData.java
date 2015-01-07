package hamster.payment;

import hamster.balance.AmountBuilder;
import hamster.validation.Data;
import hamster.validation.ValidationException;
import hamster.model.Amount;
import org.apache.commons.lang3.StringUtils;

public class PaymentData implements Data<PaymentData> {

    private static final long serialVersionUID = 1L;

    private String merchant;
    private String transaction;
    private AmountBuilder amount = AmountBuilder.create();

    public String getMerchant() {
        return merchant;
    }

    public PaymentData setMerchant(String merchant) {
        this.merchant = merchant;
        return this;
    }

    public String getTransaction() {
        return transaction;
    }

    public PaymentData setTransaction(String transaction) {
        this.transaction = transaction;
        return this;
    }

    public Amount getAmount() {
        return amount.build();
    }

    public PaymentData setAmountValue(String value) {
        this.amount.value(value);
        return this;
    }

    public PaymentData setAmountCurrency(String currency) {
        this.amount.currency(currency);
        return this;
    }

    @Override
    public PaymentData validate() {
        if(StringUtils.isEmpty(merchant)
                || getAmount().isEmpty()){
            throw new ValidationException("Error while payment creation data validation");
        }
        return this;
    }


}
