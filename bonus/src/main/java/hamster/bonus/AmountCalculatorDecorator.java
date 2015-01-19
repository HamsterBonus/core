package hamster.bonus;

import com.google.common.base.Preconditions;
import hamster.model.Amount;

public class AmountCalculatorDecorator implements AmountCalculator {

    private PartnerPossibility partnerPossibility;
    private AmountCalculator bonusAmountCalculator;

    public AmountCalculatorDecorator(PartnerPossibility partnerPossibility,
                                     AmountCalculator bonusAmountCalculator) {
        this.partnerPossibility = Preconditions.checkNotNull(partnerPossibility);
        this.bonusAmountCalculator = Preconditions.checkNotNull(bonusAmountCalculator);
    }

    @Override
    public Amount calculate(BonusData data, String partnerId) {
        // calculate bonus amount
        Amount bonusAmount = bonusAmountCalculator.calculate(data, partnerId);
        // check partner
        return partnerPossibility.check(partnerId, bonusAmount);
    }
}
