package hamster.bonus;

import com.google.common.base.Preconditions;
import hamster.balance.AmountBuilder;
import hamster.model.Amount;
import hamster.model.BonusProgram;

public class SimpleAmountCalculator implements AmountCalculator {

    private ProgramChooser programChooser;

    public SimpleAmountCalculator(ProgramChooser programChooser) {
        this.programChooser = Preconditions.checkNotNull(programChooser);
    }

    @Override
    public Amount calculate(BonusData data, String partner) {
        // choose bonus program, check that program exists or active
        BonusProgram program = programChooser.get(data, partner);
        // calculate bonus amount if value is empty using bonus program data or it's not possible to change program value
        if(program.isCanBeChanged()
                && !data.getAmount().isEmpty()){
            return data.getAmount();
        }
        return AmountBuilder.create(data.getPayment().getAmount())
                .multiply()
                    .value(program.getPercent())
                        .build();
    }
}
