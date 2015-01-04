package hamster.bonus;

import hamster.model.Amount;

public interface CreationData extends hamster.payment.CreationData{

    Amount getBonusAmount();

}
