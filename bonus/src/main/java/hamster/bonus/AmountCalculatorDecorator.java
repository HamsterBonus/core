package hamster.bonus;

import com.google.common.base.Preconditions;
import hamster.model.Amount;
import hamster.model.Partner;

public class AmountCalculatorDecorator implements AmountCalculator {

    private PartnerChooser partnerChooser;
    private PartnerPossibility partnerPossibility;
    private AmountCalculator bonusAmountCalculator;

    public AmountCalculatorDecorator(PartnerChooser partnerChooser,
                                     PartnerPossibility partnerPossibility,
                                     AmountCalculator bonusAmountCalculator) {
        this.partnerChooser = Preconditions.checkNotNull(partnerChooser);
        this.partnerPossibility = Preconditions.checkNotNull(partnerPossibility);
        this.bonusAmountCalculator = Preconditions.checkNotNull(bonusAmountCalculator);
    }

    @Override
    public Amount calculate(BonusData data, String partnerId) {
        // choose partner checking status
        Partner partner = partnerChooser.get(data, partnerId);
        // calculate bonus amount
        Amount bonusAmount = bonusAmountCalculator.calculate(data, partner.getId());
        // check partner
        return partnerPossibility.check(partner.getId(), bonusAmount);
    }
}
