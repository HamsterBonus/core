package hamster.bonus;

import hamster.balance.AmountBuilder;
import hamster.model.Amount;

public class SimpleCreationData implements CreationData {

    private static final long serialVersionUID = 1L;

    private String merchant;
    private String transaction;
    private String program;
    private AmountBuilder amount = AmountBuilder.create();
    private AmountBuilder bonusAmount = AmountBuilder.create();

    @Override
    public String getMerchant() {
        return merchant;
    }

    public SimpleCreationData setMerchant(String merchant) {
        this.merchant = merchant;
        return this;
    }

    @Override
    public String getTransaction() {
        return transaction;
    }

    public SimpleCreationData setTransaction(String transaction) {
        this.transaction = transaction;
        return this;
    }

    @Override
    public Amount getAmount() {
        return amount.build();
    }

    public SimpleCreationData setAmountValue(String value) {
        this.amount.value(value);
        return this;
    }

    public SimpleCreationData setAmountCurrency(String currency) {
        this.amount.currency(currency);
        return this;
    }

    @Override
    public Amount getBonusAmount() {
        return bonusAmount.build();
    }

    public SimpleCreationData setBonusValue(String value) {
        this.bonusAmount.value(value);
        return this;
    }

    public SimpleCreationData setBonusCurrency(String currency) {
        this.bonusAmount.currency(currency);
        return this;
    }

    @Override
    public String getProgram() {
        return program;
    }

    public SimpleCreationData setProgram(String program) {
        this.program = program;
        return this;
    }
}
