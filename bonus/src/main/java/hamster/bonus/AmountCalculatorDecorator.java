package hamster.bonus;

import com.google.common.base.Preconditions;
import hamster.model.Amount;
import hamster.model.BonusProgram;

public class AmountCalculatorDecorator implements AmountCalculator {

    private PartnerPossibility partnerPossibility;
    private AmountCalculator bonusAmountCalculator;

    public AmountCalculatorDecorator(PartnerPossibility partnerPossibility,
                                     AmountCalculator bonusAmountCalculator) {
        this.partnerPossibility = Preconditions.checkNotNull(partnerPossibility);
        this.bonusAmountCalculator = Preconditions.checkNotNull(bonusAmountCalculator);
    }

    @Override
    public Amount calculate(BonusData data, BonusProgram program, String partnerId) {
        // calculate bonus amount
        Amount bonusAmount = bonusAmountCalculator.calculate(data, program, partnerId);
        // check partner
        return partnerPossibility.check(partnerId, bonusAmount);
    }
}
