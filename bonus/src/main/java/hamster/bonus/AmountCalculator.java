package hamster.bonus;

import hamster.model.Amount;
import hamster.model.BonusProgram;

public interface AmountCalculator {

    Amount calculate(BonusData data, BonusProgram program, String partnerId);

}
