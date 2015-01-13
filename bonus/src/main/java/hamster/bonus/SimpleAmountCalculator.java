package hamster.bonus;

import com.google.common.base.Preconditions;
import hamster.balance.AmountBuilder;
import hamster.dao.BonusProgramPartnerDao;
import hamster.error.SystemException;
import hamster.model.Amount;
import hamster.model.BonusProgram;
import hamster.model.BonusProgramPartner;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class SimpleAmountCalculator implements AmountCalculator {

    private BonusProgramPartnerDao bonusProgramPartnerDao;
    private ProgramChooser programChooser;

    public SimpleAmountCalculator(BonusProgramPartnerDao bonusProgramPartnerDao, ProgramChooser programChooser) {
        this.bonusProgramPartnerDao = Preconditions.checkNotNull(bonusProgramPartnerDao);
        this.programChooser = Preconditions.checkNotNull(programChooser);
    }

    @Override
    public Amount calculate(BonusData data, String partner) {
        // choose bonus program, check that program exists or active
        Collection<BonusProgramPartner> programs = bonusProgramPartnerDao.findByPartner(partner);
        if(CollectionUtils.isEmpty(programs)){
            throw new SystemException("The list of bonus programs for partner " + partner + " is empty");
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
