package hamster.bonus;

import hamster.balance.AmountBuilder;
import hamster.model.Amount;
import hamster.model.BonusProgram;

import java.math.BigDecimal;

public class SimpleAmountCalculator implements AmountCalculator {

    @Override
    public Amount calculate(BonusData data, BonusProgram program, String partnerId) {
        // calculate bonus amount if value is empty using bonus program data or it's not possible to change program value
        if(program.isCanBeChanged()
                && !data.getAmount().isEmpty()){
            return data.getAmount();
        }
        return AmountBuilder.create(data.getPayment().getAmount())
                .multiply()
                    .value(BigDecimal.ONE.divide(program.getPercent()))
                        .build();
    }
}
