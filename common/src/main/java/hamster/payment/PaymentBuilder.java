package hamster.payment;

import hamster.model.Amount;
import hamster.model.Payment;

public class PaymentBuilder {

    private String id;
    private String merchant;
    private String transaction;
    private Amount amount;

    public static PaymentBuilder create(){
        return new PaymentBuilder();
    }

    public static PaymentBuilder create(PaymentData data){
        return create()
                .merchant(data.getMerchant())
                .transaction(data.getTransaction())
                .amount(data.getAmount())
        ;
    }

    public static PaymentBuilder create(Payment payment){
        return create()
                .merchant(payment.getMerchant())
                .transaction(payment.getTransaction())
                .amount(payment.getAmount())
        ;
    }

    public PaymentBuilder id(String id) {
        this.id = id;
        return this;
    }

    public PaymentBuilder merchant(String merchant) {
        this.merchant = merchant;
        return this;
    }

    public PaymentBuilder transaction(String transaction) {
        this.transaction = transaction;
        return this;
    }

    public PaymentBuilder amount(Amount amount) {
        this.amount = amount;
        return this;
    }

    public Payment build(){
        return new Payment(id, merchant, transaction, amount);
    }
}
