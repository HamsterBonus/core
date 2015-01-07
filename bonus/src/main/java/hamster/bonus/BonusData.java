package hamster.bonus;

import hamster.balance.AmountBuilder;
import hamster.model.Amount;
import hamster.payment.PaymentData;
import hamster.validation.Data;

public class BonusData implements Data<BonusData> {

    private static final long serialVersionUID = 1L;

    private String program;
    private AmountBuilder amount = AmountBuilder.create();
    private PaymentData payment = new PaymentData();

    public Amount getAmount() {
        return amount.build();
    }

    public PaymentData getPayment() {
        return payment;
    }

    public BonusData setValue(String value) {
        this.amount.value(value);
        return this;
    }

    public BonusData setCurrency(String currency) {
        this.amount.currency(currency);
        return this;
    }

    public String getProgram() {
        return program;
    }

    public BonusData setProgram(String program) {
        this.program = program;
        return this;
    }

    @Override
    public BonusData validate() {
        payment.validate();
        return this;
    }
}
