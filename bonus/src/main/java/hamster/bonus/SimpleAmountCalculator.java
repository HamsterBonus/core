package hamster.bonus;

import com.google.common.base.Preconditions;
import hamster.balance.AmountBuilder;
import hamster.dao.BonusProgramMerchantDao;
import hamster.error.SystemException;
import hamster.model.Amount;
import hamster.model.BonusProgram;
import hamster.model.BonusProgramMerchant;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class SimpleAmountCalculator implements AmountCalculator {

    private BonusProgramMerchantDao bonusProgramMerchantDao;
    private ProgramChooser programChooser;

    public SimpleAmountCalculator(BonusProgramMerchantDao bonusProgramMerchantDao, ProgramChooser programChooser) {
        this.bonusProgramMerchantDao = Preconditions.checkNotNull(bonusProgramMerchantDao);
        this.programChooser = Preconditions.checkNotNull(programChooser);
    }

    @Override
    public Amount calculate(BonusData data, String merchant) {
        // choose bonus program, check that program exists or active
        Collection<BonusProgramMerchant> programs = bonusProgramMerchantDao.findByMerchant(merchant);
        if(CollectionUtils.isEmpty(programs)){
            throw new SystemException("The list of bonus programs for merchant " + merchant + " is empty");
        }
        BonusProgram program = programChooser.get(programs, data);
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
