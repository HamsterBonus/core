package hamster.bonus;

import hamster.model.Amount;

public interface AmountCalculator {

    Amount calculate(BonusData data, String merchant);

}
