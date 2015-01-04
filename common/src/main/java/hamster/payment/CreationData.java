package hamster.payment;

import hamster.model.Amount;

import java.io.Serializable;

public interface CreationData extends Serializable {

    String getMerchant();
    String getTransaction();
    Amount getAmount();

}
