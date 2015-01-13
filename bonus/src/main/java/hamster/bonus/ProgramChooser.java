package hamster.bonus;

import hamster.model.BonusProgram;
import hamster.model.BonusProgramPartner;

import java.util.Collection;

public interface ProgramChooser {

    BonusProgram get(Collection<BonusProgramPartner> programs, BonusData data);

}
