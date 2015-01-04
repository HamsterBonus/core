package hamster.bonus.bonus;

import hamster.bonus.balance.AmountBuilder;
import hamster.bonus.model.Amount;

import java.io.Serializable;

public class StartData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String merchant;
    private String transaction;
    private AmountBuilder amount = AmountBuilder.create();
    private AmountBuilder bonusAmount = AmountBuilder.create();

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public Amount getAmount() {
        return amount.build();
    }

    public void setAmountValue(String value) {
        this.amount.value(value);
    }

    public void setAmountCurrency(String currency) {
        this.amount.currency(currency);
    }

    public Amount getBonusAmount() {
        return bonusAmount.build();
    }

    public void setBonusValue(String value) {
        this.bonusAmount.value(value);
    }

    public void setBonusCurrency(String currency) {
        this.bonusAmount.currency(currency);
    }

}
