package hamster.bonus;

import hamster.model.BonusProgram;
import hamster.model.BonusProgramMerchant;

import java.util.Collection;

public interface ProgramChooser {

    BonusProgram get(Collection<BonusProgramMerchant> programs, BonusData data);

}
