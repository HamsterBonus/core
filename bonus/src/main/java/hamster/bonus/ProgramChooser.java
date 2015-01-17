package hamster.bonus;

import hamster.model.BonusProgram;
import hamster.model.ProgramPartner;

import java.util.Collection;

public interface ProgramChooser {

    BonusProgram get(Collection<ProgramPartner> programs, BonusData data);

}
